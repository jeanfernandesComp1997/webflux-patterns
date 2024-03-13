package com.sample.webfluxpatterns.sec02.client.impl

import com.sample.webfluxpatterns.sec02.client.FlightClient
import com.sample.webfluxpatterns.sec02.dto.FlightResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component("DeltaClient")
class DeltaClientImpl(
    @Value("\${sec02.delta.service}")
    baseUrl: String,
    webClientBuilder: WebClient.Builder
) : FlightClient {

    private var client: WebClient = webClientBuilder
        .baseUrl(baseUrl)
        .build()

    override fun retrieveFlights(from: String, to: String): Flux<FlightResponse> {
        return client
            .get()
            .uri("{from}/{to}", from, to)
            .retrieve()
            .bodyToFlux(FlightResponse::class.java)
            .onErrorResume { Mono.empty() }
    }
}