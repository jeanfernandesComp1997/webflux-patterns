package com.sample.webfluxpatterns.sec10.dto

import java.time.LocalDate

data class Price(
    val listPrice: Int,
    val discount: Double,
    val discountedPrice: Double,
    val amountSaved: Double,
    val endDate: LocalDate
)