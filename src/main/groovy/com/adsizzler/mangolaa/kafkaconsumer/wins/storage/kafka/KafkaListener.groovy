package com.adsizzler.mangolaa.kafkaconsumer.wins.storage.kafka

import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.constants.KafkaTopics
import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.domain.WinNotification
import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.request.WinNotificationRequest
import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.service.WinNotificationService
import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.util.Gzip
import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.util.Json
import groovy.util.logging.Slf4j
import io.vertx.kafka.client.consumer.KafkaConsumer
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

/**
 * Class that reads stream of win events from Kafka
 * Created by ankushsharma on 02/02/18.
 */
@Component
@Slf4j
class KafkaListener {

    private final KafkaConsumer<String,byte[]> kafka
    private final WinNotificationService winNotificationService

    KafkaListener(
            KafkaConsumer<String,byte[]> kafka,
            WinNotificationService winNotificationService
    )
    {
        this.kafka = kafka
        this.winNotificationService = winNotificationService
    }

    /**
     * Register the handler. This is a one time operation
     */
    @PostConstruct
    void saveWins(){

        kafka.handler{ record ->

            def offset = record.offset()
            log.debug 'Offset {}' , offset

            // payload -> Decompress Gzip -> JSON -> POJO
            def payload = record.value()
            def json = Gzip.decompress(payload)
            def winRequest = Json.toObject(json, WinNotificationRequest)
            def win = new WinNotification(winRequest)

            log.debug 'Win {}', win
            winNotificationService.save(win)
        }
        .exceptionHandler{ ex ->
            log.error '', ex
        }

        kafka.subscribe(KafkaTopics.WINS)
    }
}
