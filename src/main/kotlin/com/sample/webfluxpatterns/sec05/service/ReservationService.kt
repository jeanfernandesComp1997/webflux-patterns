package com.sample.webfluxpatterns.sec05.service

import com.sample.webfluxpatterns.sec05.dto.ReservationItemRequest
import com.sample.webfluxpatterns.sec05.dto.ReservationItemResponse
import com.sample.webfluxpatterns.sec05.dto.ReservationResponse
import com.sample.webfluxpatterns.sec05.dto.ReservationType
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.GroupedFlux
import reactor.core.publisher.Mono
import java.util.UUID
import java.util.function.Function
import java.util.stream.Collectors

@Service
class ReservationService(
    handlers: List<ReservationHandler>
) {

    private var map: Map<ReservationType, ReservationHandler> = handlers
        .associateBy(ReservationHandler::getType)
//        .stream()
//        .collect(
//            Collectors.toMap(
//                ReservationHandler::getType,
//                Function.identity()
//            )
//        )


    fun reserve(request: Flux<ReservationItemRequest>): Mono<ReservationResponse> {
        return request
            .groupBy { it.type } // splitter
            .flatMap { aggregator(it) }
            .collectList()
            .map { toResponse(it) }
    }

    private fun aggregator(
        groupedFlux: GroupedFlux<ReservationType, ReservationItemRequest>
    ): Flux<ReservationItemResponse> {
        val key = groupedFlux.key()
        val handler = map[key]!!
        return handler.reserve(groupedFlux)
    }

    private fun toResponse(responseList: List<ReservationItemResponse>): ReservationResponse {
        return ReservationResponse(
            reservationId = UUID.randomUUID(),
            price = responseList.sumOf { it.price },
            items = responseList
        )
    }
}