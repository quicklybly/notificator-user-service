package com.quicklybly.notificator.userservice.config

import com.quicklybly.notificator.userservice.dto.Alert
import com.quicklybly.notificator.userservice.dto.serialize.AlertSerializer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@Configuration
class KafkaProducerConfig {
    @Value("\${spring.kafka.bootstrap-servers}")
    private lateinit var bootstrapAddress: String

    @Value("\${spring.kafka.template.default-topic}")
    private lateinit var defaultTopic: String

    @Bean
    fun producerFactory(): ProducerFactory<String, Alert> {
        val config = mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapAddress,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to AlertSerializer::class.java,
        )
        return DefaultKafkaProducerFactory(config)
    }

    @Bean
    fun kafkaTemplate(
        producerFactory: ProducerFactory<String, Alert>
    ) = KafkaTemplate(producerFactory).apply {
        defaultTopic = this@KafkaProducerConfig.defaultTopic
    }
}