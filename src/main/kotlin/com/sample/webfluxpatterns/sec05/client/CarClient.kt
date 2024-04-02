package com.sample.webfluxpatterns.sec05.client

import com.sample.webfluxpatterns.sec05.dto.CarReservationRequest
import com.sample.webfluxpatterns.sec05.dto.CarReservationResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient

@Service
class CarClient(
    @Value("\${sec05.car.service}")
    baseUrl: String,
    webClientBuilder: WebClient.Builder
) {

    private var client: WebClient = webClientBuilder
        .clientConnector(
            ReactorClientHttpConnector(
                HttpClient.create().wiretap(true)
            )
        )
        .baseUrl(baseUrl)
        .build()

    fun reserve(request: Flux<CarReservationRequest>): Flux<CarReservationResponse> {
        return client
            .post()
            .body(request.collectList(), CarReservationRequest::class.java)
            .retrieve()
            .bodyToFlux(CarReservationResponse::class.java)
            .onErrorResume { Mono.empty() }
    }
}