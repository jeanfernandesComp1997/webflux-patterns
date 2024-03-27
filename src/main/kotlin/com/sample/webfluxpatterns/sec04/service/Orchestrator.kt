package com.sample.webfluxpatterns.sec04.service

import com.sample.webfluxpatterns.sec04.dto.OrchestrationRequestContext
import com.sample.webfluxpatterns.sec04.exception.OrderFulfillmentException
import reactor.core.publisher.Mono
import reactor.core.publisher.SynchronousSink
import java.util.function.BiConsumer
import java.util.function.Consumer
import java.util.function.Predicate

abstract class Orchestrator {

    abstract fun create(ctx: OrchestrationRequestContext): Mono<OrchestrationRequestContext>

    abstract fun isSuccess(): Predicate<OrchestrationRequestContext>

    abstract fun cancel(): Consumer<OrchestrationRequestContext>

    protected fun statusHandler(): BiConsumer<OrchestrationRequestContext, SynchronousSink<OrchestrationRequestContext>> {
        return BiConsumer { ctx, sink ->
            if (isSuccess().test(ctx)) {
                sink.next(ctx)
            } else {
                sink.error(OrderFulfillmentException())
            }
        }
    }
}