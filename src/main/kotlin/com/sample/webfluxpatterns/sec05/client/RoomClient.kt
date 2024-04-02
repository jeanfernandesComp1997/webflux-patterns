package com.sample.webfluxpatterns.sec05.client

import com.sample.webfluxpatterns.sec05.dto.RoomReservationRequest
import com.sample.webfluxpatterns.sec05.dto.RoomReservationResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class RoomClient(
    @Value("\${sec05.room.service}")
    baseUrl: String,
    webClientBuilder: WebClient.Builder
) {

    private var client: WebClient = webClientBuilder
        .baseUrl(baseUrl)
        .build()

    fun reserve(request: Flux<RoomReservationRequest>): Flux<RoomReservationResponse> {
        return client
            .post()
            .body(request, RoomReservationRequest::class.java)
            .retrieve()
            .bodyToFlux(RoomReservationResponse::class.java)
            .onErrorResume { Mono.empty() }
    }
}