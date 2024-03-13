package com.sample.webfluxpatterns.sec02.controller

import com.sample.webfluxpatterns.sec02.dto.FlightResponse
import com.sample.webfluxpatterns.sec02.service.FlightSearchService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("sec02")
class FlightsController(
    private val flightSearchService: FlightSearchService
) {

    @GetMapping("flights/{from}/{to}", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun retrieveFlights(@PathVariable from: String, @PathVariable to: String): Flux<FlightResponse> {
        return flightSearchService.retrieveFlights(from, to)
    }
}