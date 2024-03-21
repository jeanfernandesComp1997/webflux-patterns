package com.sample.webfluxpatterns.sec03.service.impl

import com.sample.webfluxpatterns.sec03.client.InventoryClient
import com.sample.webfluxpatterns.sec03.dto.OrchestrationRequestContext
import com.sample.webfluxpatterns.sec03.dto.Status
import com.sample.webfluxpatterns.sec03.service.Orchestrator
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.function.Consumer
import java.util.function.Predicate

@Service
class InventoryOrchestrator(
    private val inventoryClient: InventoryClient
) : Orchestrator() {

    override fun create(ctx: OrchestrationRequestContext): Mono<OrchestrationRequestContext> {
        return inventoryClient.deduct(ctx.inventoryRequest!!)
            .doOnNext { ctx.inventoryResponse = it }
            .thenReturn(ctx)
    }

    override fun isSuccess(): Predicate<OrchestrationRequestContext> {
        return Predicate<OrchestrationRequestContext> {
            it.inventoryResponse?.status == Status.SUCCESS
        }
    }

    override fun cancel(): Consumer<OrchestrationRequestContext> {
        return Consumer<OrchestrationRequestContext> { orchestrationRequestContext ->
            Mono.just(orchestrationRequestContext)
                .filter(isSuccess())
                .map { it.inventoryRequest }
                .flatMap { inventoryClient.restore(it!!) }
                .subscribe()
        }
    }
}