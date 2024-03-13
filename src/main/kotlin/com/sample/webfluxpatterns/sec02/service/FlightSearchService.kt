package com.sample.webfluxpatterns.sec02.service

import com.sample.webfluxpatterns.sec02.dto.FlightResponse
import reactor.core.publisher.Flux

interface FlightSearchService {

    fun retrieveFlights(from: String, to: String): Flux<FlightResponse>
}