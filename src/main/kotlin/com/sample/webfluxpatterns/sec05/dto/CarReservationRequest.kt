package com.sample.webfluxpatterns.sec05.dto

import java.time.LocalDate

data class CarReservationRequest(
    val city: String,
    val pickup: LocalDate,
    val drop: LocalDate,
    val category: String?
)