package com.sample.webfluxpatterns.sec01.dto

import java.time.LocalDate

data class PromotionResponse(
    val id: Int,
    val type: String,
    val discount: Double,
    val endDate: LocalDate
)