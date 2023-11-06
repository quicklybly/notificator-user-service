package com.quicklybly.notificator.userservice.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    @GetMapping("hello")
    fun secured(): String = "Hello from secured"
}