package com.sample.webfluxpatterns.sec04.client

import com.sample.webfluxpatterns.sec04.dto.PaymentRequest
import com.sample.webfluxpatterns.sec04.dto.PaymentResponse
import reactor.core.publisher.Mono

interface UserClient {

    fun deduct(paymentRequest: PaymentRequest): Mono<PaymentResponse>

    fun refund(paymentRequest: PaymentRequest): Mono<PaymentResponse>
}