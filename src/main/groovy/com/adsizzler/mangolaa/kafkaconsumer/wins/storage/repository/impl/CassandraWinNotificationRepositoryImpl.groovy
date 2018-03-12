package com.adsizzler.mangolaa.kafkaconsumer.wins.storage.repository.impl

import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.annotation.TestingOnly
import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.domain.WinNotification
import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.repository.WinNotificationRepository
import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.util.Assert
import com.datastax.driver.core.ResultSet
import com.datastax.driver.core.querybuilder.QueryBuilder
import com.google.common.util.concurrent.FutureCallback
import com.google.common.util.concurrent.Futures
import groovy.util.logging.Slf4j
import io.vertx.core.Future
import org.springframework.beans.factory.annotation.Value
import org.springframework.cassandra.core.CqlTemplate
import org.springframework.stereotype.Repository

import javax.annotation.PreDestroy
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
            @Value("\${wins.cassandra.wins.keyspace}") String keyspace,
            @Value("\${wins.cassandra.wins.table}") String table
    )
    {
        this.cqlTemplate = cqlTemplate
        this.table = table
        this.keyspace = keyspace
    }

    @PreDestroy
    void cleanup() {
        log.info 'Attempting to shutdown executor for class {}', this.class.name
        executor.shutdown()
    }

    @Override
    Future<Void> save(WinNotification win) {
        Assert.notNull(win, 'win cannot be null')
        def future = Future.future()

        def cassandraTimestamp = Date.from(win.timestamp.toInstant())

        def insertStatement = QueryBuilder
                                .insertInto(keyspace, table)
                                    .value('uuid', win.uuid)
                                    .value('timestamp', cassandraTimestamp)
                                    .value('markup', win.markup)
                                    .value('cr_id', win.creativeId)
                                    .value('camp_id', win.campaignId)
                                    .value('src_id', win.sourceId)
                                    .value('cl_id', win.clientId)
                                    .value('bid_req_id', win.bidReqId)
                                    .value('bid_resp_id', win.bidRespId)
                                    .value('imp_id', win.impId)
                                    .value('ad_id', win.adId)
                                    .value('price', win.clearingPrice)
                                    .value('seat_id', win.seatId)
                                    .value('cur', win.cur)
                                    .value('mbr', win.mbr)
                                    .value('loss_code', win.lossCode)

        def insertFuture = cqlTemplate.executeAsynchronously(insertStatement)

        Futures.addCallback(insertFuture, [
                onSuccess : { ResultSet resultSet ->
                    log.debug 'ResultSet {}', resultSet?.one()
                    future.complete()
                },
                onFailure : { Throwable ex ->
                    future.fail(ex)
                }

        ] as FutureCallback<ResultSet>, executor)

        future
    }

    @Override
    @TestingOnly
    Future<WinNotification> findOneByUuid(UUID uuid) {
        Assert.notNull(uuid, 'uuid cannot be null')
        def future = Future.future()
        def selectStatement = QueryBuilder
                                .select()
                                    .from(keyspace, table)
                                    .where(QueryBuilder.gt('uuid', uuid))

        def selectFuture = cqlTemplate.executeAsynchronously(selectStatement)

        Futures.addCallback(selectFuture, [
                onSuccess : { ResultSet resultSet ->
                    def win = resultSet?.one()
                    log.debug 'Win {}', win
                    future.complete(win)
                },
                onFailure : { Throwable ex ->
                    future.fail(ex)
                }
        ] as FutureCallback<ResultSet>, executor)

        future
    }
}
