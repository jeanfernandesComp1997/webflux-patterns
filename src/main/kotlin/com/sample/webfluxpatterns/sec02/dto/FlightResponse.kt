package com.sample.webfluxpatterns.sec02.dto

import java.time.LocalDate

data class FlightResponse(
    var airline: String?,
    var from: String?,
    var to: String?,
    val price: Double,
    val date: LocalDate
)
