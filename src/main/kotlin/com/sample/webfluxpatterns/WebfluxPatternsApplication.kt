package com.sample.webfluxpatterns

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.sample.webfluxpatterns.sec06"])
class WebfluxPatternsApplication

fun main(args: Array<String>) {
    runApplication<WebfluxPatternsApplication>(*args)
}
