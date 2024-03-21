package com.sample.webfluxpatterns.sec03.controller

import com.sample.webfluxpatterns.sec03.dto.OrderRequest
import com.sample.webfluxpatterns.sec03.dto.OrderResponse
import com.sample.webfluxpatterns.sec03.service.impl.OrchestratorService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("sec03")
class OrderController(
    private val orchestratorService: OrchestratorService
) {

    @PostMapping("order")
    fun placeOrder(@RequestBody orderRequest: Mono<OrderRequest>): Mono<ResponseEntity<OrderResponse>> {
        return orchestratorService.placeOrder(orderRequest)
            .map {
                ResponseEntity.ok(it)
            }
            .defaultIfEmpty(ResponseEntity.notFound().build())
    }
}