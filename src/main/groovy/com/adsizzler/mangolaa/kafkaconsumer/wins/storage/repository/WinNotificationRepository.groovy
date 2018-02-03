package com.adsizzler.mangolaa.kafkaconsumer.wins.storage.repository

import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.domain.WinNotification
import io.vertx.core.Future

/**
 * Created by ankushsharma on 02/02/18.
 */
interface WinNotificationRepository {

    /**
     * Save the WinNotification to permanent storage
     * @param win WinNotification to save
     * @return A Future with null value if succeeded, or an error otherwise
     */
    Future<Void> save(WinNotification win)

}
