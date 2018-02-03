package com.adsizzler.mangolaa.kafkaconsumer.wins.storage.request

import com.adsizzler.mangolaa.kafkaconsumer.wins.storage.jackson.ZonedDateTimeDeserializer
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

import java.time.ZonedDateTime

/**
 * Created by ankushsharma on 02/02/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class WinNotificationRequest {

    @JsonProperty(value = 'uuid', required = true)
    private final UUID uuid

    @JsonProperty(value = 'timestamp', required = true)
    @JsonDeserialize(using = ZonedDateTimeDeserializer)
    private final ZonedDateTime timestamp

    @JsonProperty(value = 'markup')
    private final String markup

    @JsonProperty(value = 'crId', required = true)
    private final Integer creativeId

    @JsonProperty(value = 'campId', required = true)
    private final Integer campaignId

    @JsonProperty(value = 'srcId', required = true)
    private final Integer sourceId

    @JsonProperty(value = "advId", required = true)
    private final Integer advId

    @JsonProperty(value = "clientId", required = true)
    private final Integer clientId

    @JsonProperty(value = 'bidReqId')
    private final String bidReqId

    @JsonProperty(value = 'bidRespId')
    private final String bidRespId

    @JsonProperty(value = 'impId')
    private final String impId

    @JsonProperty(value = 'adId')
    private final String adId

    @JsonProperty(value = 'clearingPrice')
    private final Float clearingPrice

    @JsonProperty(value = 'seatId')
    private final String seatId

    @JsonProperty(value = 'cur')
    private final String cur

    @JsonProperty(value = 'mbr')
    private final Double mbr

    @JsonProperty(value = 'lossCode')
    private final Integer lossCode
}
