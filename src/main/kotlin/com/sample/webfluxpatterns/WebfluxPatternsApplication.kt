package com.sample.webfluxpatterns

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.sample.webfluxpatterns.sec07"])
class WebfluxPatternsApplication

fun main(args: Array<String>) {
    runApplication<WebfluxPatternsApplication>(*args)
}
