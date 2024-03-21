package com.sample.webfluxpatterns.sec03.service.impl

import com.sample.webfluxpatterns.sec03.dto.OrchestrationRequestContext
import com.sample.webfluxpatterns.sec03.dto.Status
import com.sample.webfluxpatterns.sec03.service.Orchestrator
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class OrderFulfillmentService(
    private val orchestrators: List<Orchestrator>
) {

    fun placeOrder(ctx: OrchestrationRequestContext): Mono<OrchestrationRequestContext> {
        val list = orchestrators
            .map { it.create(ctx) }

        return Mono.zip(list) { it[0] }
            .cast(OrchestrationRequestContext::class.java)
            .doOnNext { updateStatus(it) }
    }

    private fun updateStatus(ctx: OrchestrationRequestContext) {
        val allSuccess = orchestrators.all { it.isSuccess().test(ctx) }
        val status = if (allSuccess) Status.SUCCESS else Status.FAILED
        ctx.finalStatus = status
    }
}