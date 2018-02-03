package com.adsizzler.mangolaa.kafkaconsumer.wins.storage.jackson

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by ankushsharma on 02/02/18.
 */
class ZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {

    @Override
    ZonedDateTime deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ZonedDateTime.parse(
                parser.text, DateTimeFormatter.ISO_ZONED_DATE_TIME
        )
    }

}
