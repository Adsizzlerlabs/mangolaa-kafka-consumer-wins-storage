package com.adsizzler.mangolaa.kafkaconsumer.wins.storage.request

import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.jackson.UUIDDeserializer
import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.jackson.ZonedDateTimeDeserializer
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

import java.time.ZonedDateTime

/**
 * Created by ankushsharma on 02/02/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class WinNotificationRequest {

    @JsonProperty(value = 'uuid', required = true)
    @JsonDeserialize(using = UUIDDeserializer)
    final UUID uuid

    @JsonProperty(value = 'timestamp', required = true)
    @JsonDeserialize(using = ZonedDateTimeDeserializer)
    final ZonedDateTime timestamp

    @JsonProperty(value = 'markup')
    final String markup

    @JsonProperty(value = 'crId', required = true)
    final Integer creativeId

    @JsonProperty(value = 'campId', required = true)
    final Integer campaignId

    @JsonProperty(value = 'srcId', required = true)
    final Integer sourceId

    @JsonProperty(value = "advId", required = true)
    final Integer advId

    @JsonProperty(value = "clientId", required = true)
    final Integer clientId

    @JsonProperty(value = 'bidReqId')
    final String bidReqId

    @JsonProperty(value = 'bidRespId')
    final String bidRespId

    @JsonProperty(value = 'impId')
    final String impId

    @JsonProperty(value = 'adId')
    final String adId

    @JsonProperty(value = 'clearingPrice')
    final Float clearingPrice

    @JsonProperty(value = 'seatId')
    final String seatId

    @JsonProperty(value = 'cur')
    final String cur

    @JsonProperty(value = 'mbr')
    final Double mbr

    @JsonProperty(value = 'lossCode')
    final Integer lossCode
}
