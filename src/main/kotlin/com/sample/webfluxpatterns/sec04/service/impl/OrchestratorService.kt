package com.sample.webfluxpatterns.sec04.service.impl

import com.sample.webfluxpatterns.sec04.dto.OrchestrationRequestContext
import com.sample.webfluxpatterns.sec04.dto.OrderRequest
import com.sample.webfluxpatterns.sec04.dto.OrderResponse
import com.sample.webfluxpatterns.sec04.dto.Status
import com.sample.webfluxpatterns.sec04.util.DebugUtil
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class OrchestratorService(
    private val orderFulfillmentService: OrderFulfillmentService,
    private val cancellationService: OrderCancellationService
) {

    fun placeOrder(mono: Mono<OrderRequest>): Mono<OrderResponse> {
        return mono
            .map {
                OrchestrationRequestContext(
                    orderRequest = it
                )
            }
            .flatMap { orderFulfillmentService.placeOrder(it) }
            .doOnNext { doOrderPostProcessing(it) }
            .doOnNext { DebugUtil.print(it) } // only for debugging in dev environment
            .map { toOrderResponse(it) }
    }

    private fun doOrderPostProcessing(ctx: OrchestrationRequestContext) {
        if (Status.FAILED == ctx.finalStatus) {
            cancellationService.cancelOrder(ctx)
        }
    }

    private fun toOrderResponse(ctx: OrchestrationRequestContext): OrderResponse {
        val isSuccess = Status.SUCCESS == ctx.finalStatus
        val address = if (isSuccess) ctx.shippingResponse!!.address else null
        val deliveryDate = if (isSuccess) ctx.shippingResponse!!.expectedDelivery else null

        return OrderResponse(
            userId = ctx.orderRequest.userId,
            productId = ctx.orderRequest.productId,
            orderId = ctx.orderId,
            status = ctx.finalStatus!!,
            shippingAddress = address,
            expectedDelivery = deliveryDate
        )
    }
}