package com.sample.webfluxpatterns.sec03.client

import com.sample.webfluxpatterns.sec03.dto.PaymentRequest
import com.sample.webfluxpatterns.sec03.dto.PaymentResponse
import reactor.core.publisher.Mono

interface UserClient {

    fun deduct(paymentRequest: PaymentRequest): Mono<PaymentResponse>

    fun refund(paymentRequest: PaymentRequest): Mono<PaymentResponse>
}