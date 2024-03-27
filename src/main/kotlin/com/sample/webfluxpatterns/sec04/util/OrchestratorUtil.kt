package com.sample.webfluxpatterns.sec04.util

import com.sample.webfluxpatterns.sec04.dto.InventoryRequest
import com.sample.webfluxpatterns.sec04.dto.OrchestrationRequestContext
import com.sample.webfluxpatterns.sec04.dto.PaymentRequest
import com.sample.webfluxpatterns.sec04.dto.ShippingRequest

class OrchestratorUtil {

    companion object {

        fun buildPaymentRequest(ctx: OrchestrationRequestContext) {
            val paymentRequest = PaymentRequest(
                userId = ctx.orderRequest.userId,
                amount = ctx.productPrice!! * ctx.orderRequest.quantity,
                orderId = ctx.orderId
            )
            ctx.paymentRequest = paymentRequest
        }

        fun buildInventoryRequest(ctx: OrchestrationRequestContext) {
            val inventoryRequest = InventoryRequest(
                paymentId = ctx.paymentResponse!!.paymentId,
                productId = ctx.orderRequest.productId,
                quantity = ctx.orderRequest.quantity
            )
            ctx.inventoryRequest = inventoryRequest
        }

        fun buildShippingRequest(ctx: OrchestrationRequestContext) {
            val shippingRequest = ShippingRequest(
                quantity = ctx.orderRequest.quantity,
                userId = ctx.orderRequest.userId,
                inventoryId = ctx.inventoryResponse!!.inventoryId
            )
            ctx.shippingRequest = shippingRequest
        }
    }
}