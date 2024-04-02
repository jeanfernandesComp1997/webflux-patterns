package com.sample.webfluxpatterns.sec05.dto

import java.util.UUID

data class ReservationResponse(
    val reservationId: UUID,
    val price: Int,
    val items: List<ReservationItemResponse>
)