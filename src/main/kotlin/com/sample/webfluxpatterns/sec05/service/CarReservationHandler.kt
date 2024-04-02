package com.sample.webfluxpatterns.sec05.service

import com.sample.webfluxpatterns.sec05.client.CarClient
import com.sample.webfluxpatterns.sec05.dto.ReservationType
import com.sample.webfluxpatterns.sec05.dto.ReservationItemRequest
import com.sample.webfluxpatterns.sec05.dto.ReservationItemResponse
import com.sample.webfluxpatterns.sec05.dto.CarReservationRequest
import com.sample.webfluxpatterns.sec05.dto.CarReservationResponse
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class CarReservationHandler(
    private val carClient: CarClient
) : ReservationHandler() {

    override fun getType(): ReservationType {
        return ReservationType.CAR
    }

    override fun reserve(request: Flux<ReservationItemRequest>): Flux<ReservationItemResponse> {
        return request
            .map(this::toCarRequest)
            .transform { carClient.reserve(it) }
            .map(this::toResponse)
    }

    private fun toCarRequest(request: ReservationItemRequest): CarReservationRequest {
        return CarReservationRequest(
            city = request.city,
            pickup = request.from,
            drop = request.to,
            category = request.category
        )
    }

    private fun toResponse(response: CarReservationResponse): ReservationItemResponse {
        return ReservationItemResponse(
            reservationId = response.reservationId,
            type = getType(),
            city = response.city,
            from = response.pickup,
            to = response.drop,
            category = response.category,
            price = response.price
        )
    }
}