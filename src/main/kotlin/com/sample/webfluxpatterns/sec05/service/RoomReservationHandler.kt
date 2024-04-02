package com.sample.webfluxpatterns.sec05.service

import com.sample.webfluxpatterns.sec05.client.RoomClient
import com.sample.webfluxpatterns.sec05.dto.ReservationItemRequest
import com.sample.webfluxpatterns.sec05.dto.ReservationItemResponse
import com.sample.webfluxpatterns.sec05.dto.ReservationType
import com.sample.webfluxpatterns.sec05.dto.RoomReservationRequest
import com.sample.webfluxpatterns.sec05.dto.RoomReservationResponse
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class RoomReservationHandler(
    private val roomClient: RoomClient
) : ReservationHandler() {

    override fun getType(): ReservationType {
        return ReservationType.ROOM
    }

    override fun reserve(request: Flux<ReservationItemRequest>): Flux<ReservationItemResponse> {
        return request
            .map(this::toRoomRequest)
            .transform { roomClient.reserve(it) }
            .map(this::toResponse)
    }

    private fun toRoomRequest(request: ReservationItemRequest): RoomReservationRequest {
        return RoomReservationRequest(
            city = request.city,
            checkIn = request.from,
            checkOut = request.to,
            category = request.category
        )
    }

    private fun toResponse(response: RoomReservationResponse): ReservationItemResponse {
        return ReservationItemResponse(
            reservationId = response.reservationId,
            type = getType(),
            city = response.city,
            from = response.checkIn,
            to = response.checkOut,
            category = response.category,
            price = response.price
        )
    }
}