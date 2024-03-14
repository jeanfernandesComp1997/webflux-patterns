package com.sample.webfluxpatterns.sec03.client

import com.sample.webfluxpatterns.sec03.dto.ShippingRequest
import com.sample.webfluxpatterns.sec03.dto.ShippingResponse
import reactor.core.publisher.Mono

interface ShippingClient {

    fun schedule(shippingRequest: ShippingRequest): Mono<ShippingResponse>

    fun cancel(shippingRequest: ShippingRequest): Mono<ShippingResponse>
}