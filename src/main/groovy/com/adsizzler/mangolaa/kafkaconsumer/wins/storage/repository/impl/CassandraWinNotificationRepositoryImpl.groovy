package com.adsizzler.mangolaa.kafkaconsumer.wins.storage.repository.impl

import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.domain.WinNotification
import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.jackson.ZonedDateTimeDeserializer
import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.repository.WinNotificationRepository
import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.util.Assert
import com.datastax.driver.core.ResultSet
import com.datastax.driver.core.querybuilder.QueryBuilder
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.google.common.util.concurrent.FutureCallback
import com.google.common.util.concurrent.Futures
import groovy.util.logging.Slf4j
import io.vertx.core.Future
import org.springframework.beans.factory.annotation.Value
import org.springframework.cassandra.core.CqlTemplate
import org.springframework.stereotype.Repository

import javax.annotation.PreDestroy
import java.time.ZonedDateTime
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


/**
 * Created by ankushsharma on 02/02/18.
 */
@Repository
@Slf4j
class CassandraWinNotificationRepositoryImpl implements WinNotificationRepository{

    private static final ExecutorService executor = Executors.newSingleThreadExecutor()

    private final String keyspace
    private final String table
    private final CqlTemplate cqlTemplate

    CassandraWinNotificationRepositoryImpl(
            CqlTemplate cqlTemplate,
            @Value("\$wins.cassandra.wins.keyspace") String keyspace,
            @Value("\$wins.cassandra.wins.table") String table
    )
    {
        this.cqlTemplate = cqlTemplate
        this.table = table
        this.keyspace = keyspace
    }

    @PreDestroy
    void cleanup(){
        log.info 'Attempting to shutdown executor for class {}', this.class.name
    }

    @Override
    Future<Void> save(WinNotification win) {
        Assert.notNull(win, 'win cannot be null')
        def future = Future.future()

        // TODO : More values here
        def insertStatement = QueryBuilder
                                .insertInto(keyspace, table)
                                .value('uuid', win.uuid)
                                .value('timestamp', win.timestamp)

        def insertFuture = cqlTemplate.executeAsynchronously(insertStatement)

        Futures.addCallback(insertFuture, [
                onSuccess : { ResultSet resultSet ->
                    future.complete()
                },
                onFailure : { Throwable ex ->
                    log.error '', ex
                }

        ] as FutureCallback<ResultSet>, executor)
        future
    }

}
