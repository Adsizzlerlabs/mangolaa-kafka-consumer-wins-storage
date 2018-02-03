package com.adsizzler.mangolaa.kafkaconsumer.wins.storage.config

import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.util.EnvironmentUtil
import io.vertx.core.Vertx
import io.vertx.kafka.client.consumer.KafkaConsumer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.ByteArrayDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by Ankush on 13/04/17.
 */
@Configuration
class KafkaConsumerConfig {

    @Autowired
    Vertx vertx

    @Autowired
    EnvironmentUtil env

    @Bean("kafkaConsumer")
    KafkaConsumer<String,byte[]> kafkaConsumer(){
        Properties config = new Properties()
        String host
        if(env.isDev() || env.isTest()){
            host = "localhost:9092"
        }
        else{

        }
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,host)
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class)
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class)
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "mangolaa-wins-storage-group" + UUID.randomUUID())
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true")
        return KafkaConsumer.create(vertx, config)
    }


}
