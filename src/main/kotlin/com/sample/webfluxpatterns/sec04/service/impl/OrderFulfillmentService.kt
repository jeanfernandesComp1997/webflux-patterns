package com.sample.webfluxpatterns.sec04.service.impl

import com.sample.webfluxpatterns.sec04.client.ProductClient
import com.sample.webfluxpatterns.sec04.dto.OrchestrationRequestContext
import com.sample.webfluxpatterns.sec04.dto.Status
import com.sample.webfluxpatterns.sec04.util.OrchestratorUtil
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class OrderFulfillmentService(
    private val productClient: ProductClient,
    private val paymentOrchestrator: PaymentOrchestrator,
    private val inventoryOrchestrator: InventoryOrchestrator,
    private val shippingOrchestrator: ShippingOrchestrator
) {

    fun placeOrder(ctx: OrchestrationRequestContext): Mono<OrchestrationRequestContext> {
        return getProduct(ctx)
            .doOnNext(OrchestratorUtil::buildPaymentRequest)
            .flatMap { requestContext ->
                paymentOrchestrator.create(requestContext)
            }
            .doOnNext(OrchestratorUtil::buildInventoryRequest)
            .flatMap { requestContext ->
                inventoryOrchestrator.create(requestContext)
            }
            .doOnNext(OrchestratorUtil::buildShippingRequest)
            .flatMap { requestContext ->
                shippingOrchestrator.create(requestContext)
            }
            .doOnNext { requestContext ->
                requestContext.finalStatus = Status.SUCCESS
            }
            .doOnError { ctx.finalStatus = Status.FAILED }
            .onErrorReturn(ctx)
    }

    private fun getProduct(ctx: OrchestrationRequestContext): Mono<OrchestrationRequestContext> {
        return productClient
            .retrieveProduct(ctx.orderRequest.productId)
            .map { it.price }
            .doOnNext { ctx.productPrice = it }
            .map { ctx }
    }
}