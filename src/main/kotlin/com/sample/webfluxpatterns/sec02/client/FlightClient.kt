package com.sample.webfluxpatterns.sec02.client

import com.sample.webfluxpatterns.sec02.dto.FlightResponse
import reactor.core.publisher.Flux

interface FlightClient {

    fun retrieveFlights(from: String, to: String): Flux<FlightResponse>
}