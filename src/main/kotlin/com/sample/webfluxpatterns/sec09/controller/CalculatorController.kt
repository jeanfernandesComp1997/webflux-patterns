package com.sample.webfluxpatterns.sec09.controller

import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("sec09")
class CalculatorController {

    // CPU intensive task
    // 5 requests / 20 seconds
    @GetMapping("calculator/{input}")
    @RateLimiter(name = "calculator-service", fallbackMethod = "fallback")
    fun doubleInput(@PathVariable input: Int): Mono<ResponseEntity<Int>> {
        return Mono.fromSupplier { input * 2 }.map { ResponseEntity.ok(it) }
    }

    fun fallback(input: Int, ex: Throwable): Mono<ResponseEntity<String>> {
        return Mono.just(ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(ex.message))
    }
}