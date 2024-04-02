package com.sample.webfluxpatterns.sec05.dto

import java.time.LocalDate
import java.util.UUID

data class CarReservationResponse(
    val reservationId: UUID,
    val city: String,
    val pickup: LocalDate,
    val drop: LocalDate,
    val category: String,
    val price: Int
)
