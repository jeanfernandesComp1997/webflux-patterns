package com.sample.webfluxpatterns.sec04.client.impl

import com.sample.webfluxpatterns.sec04.client.UserClient
import com.sample.webfluxpatterns.sec04.dto.PaymentRequest
import com.sample.webfluxpatterns.sec04.dto.PaymentResponse
import com.sample.webfluxpatterns.sec04.dto.Status
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.*

@Component
class UserClientImpl(
    @Value("\${sec04.user.service}")
    baseUrl: String,
    webClientBuilder: WebClient.Builder
) : UserClient {

    companion object {
        private const val DEDUCT = "deduct"
        private const val REFUND = "refund"
    }

    private var client: WebClient = webClientBuilder
        .baseUrl(baseUrl)
        .build()

    override fun deduct(paymentRequest: PaymentRequest): Mono<PaymentResponse> {
        return callService(DEDUCT, paymentRequest)
    }

    override fun refund(paymentRequest: PaymentRequest): Mono<PaymentResponse> {
        return callService(REFUND, paymentRequest)
    }

    private fun callService(endpoint: String, paymentRequest: PaymentRequest): Mono<PaymentResponse> {
        return client
            .post()
            .uri(endpoint)
            .bodyValue(paymentRequest)
            .retrieve()
            .bodyToMono(PaymentResponse::class.java)
            .onErrorReturn(buildErrorResponse(paymentRequest))
    }

    private fun buildErrorResponse(paymentRequest: PaymentRequest): PaymentResponse {
        return PaymentResponse(
            paymentId = UUID(0L, 0L),
            userId = paymentRequest.userId,
            name = null,
            balance = paymentRequest.amount,
            status = Status.FAILED
        )
    }
}