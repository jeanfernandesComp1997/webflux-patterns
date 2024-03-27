package com.sample.webfluxpatterns.sec04.client.impl

import com.sample.webfluxpatterns.sec04.client.ShippingClient
import com.sample.webfluxpatterns.sec04.dto.ShippingRequest
import com.sample.webfluxpatterns.sec04.dto.ShippingResponse
import com.sample.webfluxpatterns.sec04.dto.Status
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.util.*

@Component
class ShippingClientImpl(
    @Value("\${sec04.shipping.service}")
    baseUrl: String,
    webClientBuilder: WebClient.Builder
) : ShippingClient {

    companion object {
        private const val SCHEDULE = "schedule"
        private const val CANCEL = "cancel"
    }

    private var client: WebClient = webClientBuilder
        .baseUrl(baseUrl)
        .build()

    override fun schedule(shippingRequest: ShippingRequest): Mono<ShippingResponse> {
        return callService(SCHEDULE, shippingRequest)
    }

    override fun cancel(shippingRequest: ShippingRequest): Mono<ShippingResponse> {
        return callService(CANCEL, shippingRequest)
    }

    private fun callService(endpoint: String, shippingRequest: ShippingRequest): Mono<ShippingResponse> {
        return client
            .post()
            .uri(endpoint)
            .bodyValue(shippingRequest)
            .retrieve()
            .bodyToMono(ShippingResponse::class.java)
            .onErrorReturn(buildErrorResponse(shippingRequest))
    }

    private fun buildErrorResponse(shippingRequest: ShippingRequest): ShippingResponse {
        return ShippingResponse(
            shippingId = UUID(0L, 0L),
            quantity = shippingRequest.quantity,
            status = Status.FAILED,
            expectedDelivery = null,
            address = null
        )
    }
}