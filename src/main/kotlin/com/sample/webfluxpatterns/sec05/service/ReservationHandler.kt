package com.sample.webfluxpatterns.sec05.service

import com.sample.webfluxpatterns.sec05.dto.ReservationItemRequest
import com.sample.webfluxpatterns.sec05.dto.ReservationItemResponse
import com.sample.webfluxpatterns.sec05.dto.ReservationType
import reactor.core.publisher.Flux

abstract class ReservationHandler {

    abstract fun getType(): ReservationType
    abstract fun reserve(request: Flux<ReservationItemRequest>): Flux<ReservationItemResponse>
}