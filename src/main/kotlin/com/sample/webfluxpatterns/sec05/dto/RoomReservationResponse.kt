package com.sample.webfluxpatterns.sec05.dto

import java.time.LocalDate
import java.util.UUID

data class RoomReservationResponse(
    val reservationId: UUID,
    val city: String,
    val checkIn: LocalDate,
    val checkOut: LocalDate,
    val category: String,
    val price: Int
)
