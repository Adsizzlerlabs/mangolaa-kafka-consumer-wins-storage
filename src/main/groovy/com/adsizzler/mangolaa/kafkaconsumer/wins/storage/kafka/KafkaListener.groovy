package com.adsizzler.mangolaa.kafkaconsumer.wins.storage.kafka

import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.constants.KafkaTopics
import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.domain.WinNotification
import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.service.WinNotificationService
import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.util.Gzip
import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.util.Json
import groovy.util.logging.Slf4j
import io.vertx.kafka.client.consumer.KafkaConsumer
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

/**
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

    @PostConstruct
    void saveWins(){

        kafka.handler{ record ->

            def offset = record.offset()
            log.debug 'Offset {}' , offset

            // payload -> Decompress Gzip -> JSON -> POJO
            def payload = record.value()
            def json = Gzip.decompress(payload)
            def winNotification = Json.toObject(json, WinNotification)

            log.debug 'Win {}', winNotification
            winNotificationService.save(winNotification)

        }
        .exceptionHandler{ ex ->
            log.error '', ex
        }

        kafka.subscribe(KafkaTopics.WINS)
    }
}
