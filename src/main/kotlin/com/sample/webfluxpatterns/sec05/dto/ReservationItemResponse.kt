package com.sample.webfluxpatterns.sec05.dto

import java.time.LocalDate
import java.util.UUID

data class ReservationItemResponse(
    val reservationId: UUID,
    val type: ReservationType,
    val category: String,
    val city: String,
    val from: LocalDate,
    val to: LocalDate,
    val price: Int
)
