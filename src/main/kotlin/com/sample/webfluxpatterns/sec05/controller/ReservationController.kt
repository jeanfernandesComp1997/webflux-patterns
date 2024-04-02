package com.sample.webfluxpatterns.sec05.controller

import com.sample.webfluxpatterns.sec05.dto.ReservationItemRequest
import com.sample.webfluxpatterns.sec05.dto.ReservationResponse
import com.sample.webfluxpatterns.sec05.service.ReservationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("sec05")
class ReservationController(
    private val reservationService: ReservationService
) {

    @PostMapping("reserve")
    fun reserve(@RequestBody request: Flux<ReservationItemRequest>): Mono<ReservationResponse> {
        return reservationService.reserve(request)
    }
}