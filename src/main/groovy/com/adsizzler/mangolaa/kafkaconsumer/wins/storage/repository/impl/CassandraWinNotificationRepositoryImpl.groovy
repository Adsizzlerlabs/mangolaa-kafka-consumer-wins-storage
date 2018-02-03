package com.adsizzler.mangolaa.kafkaconsumer.wins.storage.repository.impl

import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.domain.WinNotification
import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.repository.WinNotificationRepository
import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.util.Assert
import com.datastax.driver.core.querybuilder.Insert
import com.datastax.driver.core.querybuilder.QueryBuilder
import groovy.util.logging.Slf4j
import io.vertx.core.Future
import org.springframework.cassandra.core.CqlTemplate
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.stereotype.Repository


/**
 * Created by ankushsharma on 02/02/18.
 */
@Repository
@Slf4j
class CassandraWinNotificationRepositoryImpl implements WinNotificationRepository{

    private static final String WINS_TABLE = "wins"
    private final CqlTemplate cqlTemplate

    CassandraWinNotificationRepositoryImpl(CqlTemplate cqlTemplate){
        this.cqlTemplate = cqlTemplate
    }

    @Override
    Future<Void> save(WinNotification win) {
        Assert.notNull(win, 'win cannot be null')
        def future = Future.future()


        future
    }
}
