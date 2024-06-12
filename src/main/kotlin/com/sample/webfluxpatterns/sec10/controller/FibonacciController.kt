package com.sample.webfluxpatterns.sec10.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("sec10")
class FibonacciController {

    // 0, 1, 1, 2, 3, 5, 8, 13

    // CPU intensive task
    @GetMapping("fibonacci/{input}")
    fun fibonacci(@PathVariable input: Long): Mono<ResponseEntity<Long>> {
        return Mono.fromSupplier { findFibonacci(input) }.map { ResponseEntity.ok(it) }
    }

    private fun findFibonacci(input: Long): Long {
        return if (input < 2) {
            return input
        } else {
            findFibonacci(input - 1) + findFibonacci(input - 2)
        }
    }
}