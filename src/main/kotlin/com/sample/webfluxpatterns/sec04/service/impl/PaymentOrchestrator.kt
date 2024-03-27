package com.sample.webfluxpatterns.sec04.service.impl

import com.sample.webfluxpatterns.sec04.client.UserClient
import com.sample.webfluxpatterns.sec04.dto.OrchestrationRequestContext
import com.sample.webfluxpatterns.sec04.dto.Status
import com.sample.webfluxpatterns.sec04.service.Orchestrator
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.function.Consumer
import java.util.function.Predicate

@Service
class PaymentOrchestrator(
    private val userClient: UserClient
) : Orchestrator() {

    override fun create(ctx: OrchestrationRequestContext): Mono<OrchestrationRequestContext> {
        return userClient.deduct(ctx.paymentRequest!!)
            .doOnNext { ctx.paymentResponse = it }
            .thenReturn(ctx)
            .handle(statusHandler())
    }

    override fun isSuccess(): Predicate<OrchestrationRequestContext> {
        return Predicate<OrchestrationRequestContext> { ctx ->
            Status.SUCCESS == ctx.paymentResponse?.status
        }
    }

    override fun cancel(): Consumer<OrchestrationRequestContext> {
        return Consumer<OrchestrationRequestContext> { ctx ->
            Mono.just(ctx)
                .filter(isSuccess())
                .map { it.paymentRequest }
                .flatMap { userClient.refund(it!!) }
                .subscribe()
        }
    }
}