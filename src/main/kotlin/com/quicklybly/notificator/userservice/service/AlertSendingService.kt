package com.quicklybly.notificator.userservice.service

import com.quicklybly.notificator.userservice.dto.Alert
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class AlertSendingService(val kafkaTemplate: KafkaTemplate<String, Alert>) {
    fun sendAlert(alert: Alert): CompletableFuture<SendResult<String, Alert>> =
        kafkaTemplate.sendDefault(alert)
}