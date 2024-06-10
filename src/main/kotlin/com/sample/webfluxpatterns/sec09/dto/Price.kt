package com.sample.webfluxpatterns.sec09.dto

import java.time.LocalDate

data class Price(
    val listPrice: Int,
    val discount: Double,
    val discountedPrice: Double,
    val amountSaved: Double,
    val endDate: LocalDate
)