package com.sample.webfluxpatterns.sec03.util

import com.sample.webfluxpatterns.sec03.dto.InventoryRequest
import com.sample.webfluxpatterns.sec03.dto.OrchestrationRequestContext
import com.sample.webfluxpatterns.sec03.dto.PaymentRequest
import com.sample.webfluxpatterns.sec03.dto.ShippingRequest

class OrchestratorUtil {

    companion object {

        fun buildRequestContext(ctx: OrchestrationRequestContext) {
            buildPaymentRequest(ctx)
            buildShippingRequest(ctx)
            buildInventoryRequest(ctx)
        }

        private fun buildPaymentRequest(ctx: OrchestrationRequestContext) {
            val paymentRequest = PaymentRequest(
                userId = ctx.orderRequest.userId,
                amount = ctx.productPrice!! * ctx.orderRequest.quantity,
                orderId = ctx.orderId
            )
            ctx.paymentRequest = paymentRequest
        }

        private fun buildInventoryRequest(ctx: OrchestrationRequestContext) {
            val inventoryRequest = InventoryRequest(
                orderId = ctx.orderId,
                productId = ctx.orderRequest.productId,
                quantity = ctx.orderRequest.quantity
            )
            ctx.inventoryRequest = inventoryRequest
        }

        private fun buildShippingRequest(ctx: OrchestrationRequestContext) {
            val shippingRequest = ShippingRequest(
                quantity = ctx.orderRequest.quantity,
                userId = ctx.orderRequest.userId,
                orderId = ctx.orderId
            )
            ctx.shippingRequest = shippingRequest
        }
    }
}