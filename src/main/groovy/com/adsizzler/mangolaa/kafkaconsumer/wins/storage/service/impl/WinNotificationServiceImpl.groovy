package com.adsizzler.mangolaa.kafkaconsumer.wins.storage.service.impl

import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.domain.WinNotification
import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.repository.WinNotificationRepository
import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.service.WinNotificationService
import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.util.Assert
import groovy.util.logging.Slf4j
import io.vertx.core.Future
import org.springframework.stereotype.Service

/**
 * Created by ankushsharma on 02/02/18.
 */
@Service
@Slf4j
class WinNotificationServiceImpl implements WinNotificationService{

    private final WinNotificationRepository repository

    WinNotificationServiceImpl(WinNotificationRepository repository) {
        this.repository = repository
    }

    @Override
    Future<Void> save(WinNotification win) {
        Assert.notNull(win, 'win cannot be null')
        repository.save(win)
    }
}
