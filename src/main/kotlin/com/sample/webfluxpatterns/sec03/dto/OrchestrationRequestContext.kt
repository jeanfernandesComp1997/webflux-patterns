package com.sample.webfluxpatterns.sec03.dto

import java.util.UUID

data class OrchestrationRequestContext(
    val orderId: UUID = UUID.randomUUID(),
    var orderRequest: OrderRequest,
    var productPrice: Int?,
    var paymentRequest: PaymentRequest?,
    var paymentResponse: PaymentResponse?,
    var inventoryRequest: InventoryRequest?,
    var inventoryResponse: InventoryResponse?,
    var shippingRequest: ShippingRequest?,
    var shippingResponse: ShippingResponse?,
    var finalStatus: Status?
)
