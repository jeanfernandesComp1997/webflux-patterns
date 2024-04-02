package com.sample.webfluxpatterns.sec05.dto

import java.time.LocalDate

data class ReservationItemRequest(
    val type: ReservationType,
    val category: String?,
    val city: String,
    val from: LocalDate,
    val to: LocalDate
)
