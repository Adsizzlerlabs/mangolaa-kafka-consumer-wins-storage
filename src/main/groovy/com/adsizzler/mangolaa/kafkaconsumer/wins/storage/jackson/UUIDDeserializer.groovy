package com.adsizzler.mangolaa.kafkaconsumer.wins.storage.jackson

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer

/**
 * JacksonDeserializer to deserialize a String to UUID
 * Created by ankushsharma on 04/02/18.
 */
class UUIDDeserializer extends JsonDeserializer<UUID> {

    @Override
    UUID deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        UUID.fromString(p.text)
    }


}
