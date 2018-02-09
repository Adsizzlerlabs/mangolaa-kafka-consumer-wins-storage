package com.adsizzler.mangolaa.kafkaconsumer.wins.storage.domain

import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.request.WinNotificationRequest
import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.util.Assert
import groovy.transform.ToString

import java.time.ZonedDateTime


/**
 * The WinNotification object that will be saved to permanent storage
 * Created by ankushsharma on 02/02/18.
 */
@ToString(includePackage = false)
class WinNotification {

     final UUID uuid
     final ZonedDateTime timestamp
     final String markup
     final Integer creativeId
     final Integer campaignId
     final Integer sourceId
     final Integer advId
     final Integer clientId
     final String bidReqId
     final UUID bidRespId
     final String impId
     final String adId
     final Float clearingPrice
     final String seatId
     final String cur
     final Double mbr
     final Integer lossCode


    WinNotification(WinNotificationRequest request){
        Assert.notNull(request, 'request cannot be null')

        this.uuid = request.uuid
        this.timestamp = request.timestamp
        this.markup = request.markup
        this.creativeId = request.creativeId
        this.sourceId = request.sourceId
        this.campaignId = request.campaignId
        this.advId = request.advId
        this.clientId = request.clientId
        this.bidReqId = request.bidReqId
        this.bidRespId = request.bidRespId
        this.impId = request.impId
        this.adId = request.adId
        this.clearingPrice = request.clearingPrice
        this.seatId = request.seatId
        this.cur = request.cur
        this.mbr = request.mbr
        this.lossCode = request.lossCode
    }


}
