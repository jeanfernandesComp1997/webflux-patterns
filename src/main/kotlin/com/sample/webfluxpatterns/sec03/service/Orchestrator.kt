package com.sample.webfluxpatterns.sec03.service

import com.sample.webfluxpatterns.sec03.dto.OrchestrationRequestContext
import reactor.core.publisher.Mono
import java.util.function.Consumer
import java.util.function.Predicate

abstract class Orchestrator {

    abstract fun create(ctx: OrchestrationRequestContext): Mono<OrchestrationRequestContext>

    abstract fun isSuccess(): Predicate<OrchestrationRequestContext>

    abstract fun cancel(): Consumer<OrchestrationRequestContext>
}