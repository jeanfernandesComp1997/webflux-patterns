package com.sample.webfluxpatterns.sec05.dto

import java.time.LocalDate

data class RoomReservationRequest(
    val city: String,
    val checkIn: LocalDate,
    val checkOut: LocalDate,
    val category: String?
)
