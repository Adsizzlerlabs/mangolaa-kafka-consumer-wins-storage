package com.adsizzler.mangolaa.kafkaconsumer.wins.storage.config

import groovy.util.logging.Slf4j
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by ankushsharma on 01/09/17.
 */
@Configuration
@Slf4j
class VertxConfig {

    @Bean
    Vertx vertx(){
        Vertx.vertx(
                new VertxOptions()
                    //This is not at all an optimal setting, but then we are loading our db-ip file on startup on the Event loop thread
                    .setBlockedThreadCheckInterval(1)
        )
        .exceptionHandler{ ex ->
            log.error 'Unhandeled Exception {}', ex
        }
    }

}

