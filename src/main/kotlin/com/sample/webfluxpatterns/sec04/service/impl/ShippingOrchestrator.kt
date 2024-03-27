package com.sample.webfluxpatterns.sec04.service.impl

import com.sample.webfluxpatterns.sec04.client.ShippingClient
import com.sample.webfluxpatterns.sec04.dto.OrchestrationRequestContext
import com.sample.webfluxpatterns.sec04.dto.Status
import com.sample.webfluxpatterns.sec04.service.Orchestrator
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.function.Consumer
import java.util.function.Predicate

@Service
class ShippingOrchestrator(
    private val shippingClient: ShippingClient
) : Orchestrator() {

    override fun create(ctx: OrchestrationRequestContext): Mono<OrchestrationRequestContext> {
        return shippingClient.schedule(ctx.shippingRequest!!)
            .doOnNext { ctx.shippingResponse = it }
            .thenReturn(ctx)
            .handle(statusHandler())
    }

    override fun isSuccess(): Predicate<OrchestrationRequestContext> {
        return Predicate<OrchestrationRequestContext> {
            it.shippingResponse?.status == Status.SUCCESS
        }
    }

    override fun cancel(): Consumer<OrchestrationRequestContext> {
        return Consumer { orchestrationRequestContext ->
            Mono.just(orchestrationRequestContext)
                .filter(isSuccess())
                .map { it.shippingRequest }
                .flatMap { shippingClient.cancel(it!!) }
                .subscribe()
        }
    }
}