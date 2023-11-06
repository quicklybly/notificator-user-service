package com.quicklybly.notificator.userservice.controller

import com.quicklybly.notificator.userservice.dto.Alert
import com.quicklybly.notificator.userservice.service.AlertSendingService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/alerts")
class DummyNotificationController(
    val alertSendingService: AlertSendingService
) {

    @PostMapping
    fun sendAlert(@RequestBody alert: Alert): Boolean {
        return try {
            val future = alertSendingService.sendAlert(alert)
            future.get()
            true
        } catch (e: Exception) {
            // something went wrong
            false
        }
    }
}