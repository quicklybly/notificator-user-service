package com.quicklybly.notificator.userservice.dto.serialize

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.quicklybly.notificator.userservice.dto.Alert
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Serializer

class AlertSerializer : Serializer<Alert> {
    private val mapper = ObjectMapper()
    override fun serialize(topic: String?, data: Alert?): ByteArray = try {
        mapper.writeValueAsBytes(data)
    } catch (e: JsonProcessingException) {
        throw SerializationException("Error during serialization from Alert to byte[]", e)
    }
}