package com.sample.webfluxpatterns.sec02.service.impl

import com.sample.webfluxpatterns.sec02.client.FlightClient
import com.sample.webfluxpatterns.sec02.dto.FlightResponse
import com.sample.webfluxpatterns.sec02.service.FlightSearchService
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.time.Duration

@Service
class FlightSearchServiceImpl(
    @Qualifier("FrontierClient")
    private val frontierClient: FlightClient,
    @Qualifier("JetBlueClient")
    private val jetBlueClient: FlightClient,
    @Qualifier("DeltaClient")
    private val deltaClient: FlightClient
) : FlightSearchService {

    override fun retrieveFlights(from: String, to: String): Flux<FlightResponse> {
        return Flux.merge(
            deltaClient.retrieveFlights(from, to),
            jetBlueClient.retrieveFlights(from, to),
            frontierClient.retrieveFlights(from, to)
        )
            .take(Duration.ofSeconds(3))
    }
}