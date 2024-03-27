package com.sample.webfluxpatterns.sec04.dto

import java.util.*

data class OrchestrationRequestContext(
    val orderId: UUID = UUID.randomUUID(),
    var orderRequest: OrderRequest,
    var productPrice: Int? = null,
    var paymentRequest: PaymentRequest? = null,
    var paymentResponse: PaymentResponse? = null,
    var inventoryRequest: InventoryRequest? = null,
    var inventoryResponse: InventoryResponse? = null,
    var shippingRequest: ShippingRequest? = null,
    var shippingResponse: ShippingResponse? = null,
    var finalStatus: Status? = null
)
