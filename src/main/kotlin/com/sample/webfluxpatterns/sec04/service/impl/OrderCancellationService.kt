package com.sample.webfluxpatterns.sec04.service.impl

import com.sample.webfluxpatterns.sec04.dto.OrchestrationRequestContext
import com.sample.webfluxpatterns.sec04.service.Orchestrator
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import reactor.core.scheduler.Schedulers

@Service
class OrderCancellationService(
    orchestrators: List<Orchestrator>
) {

    private var sink: Sinks.Many<OrchestrationRequestContext> = Sinks.many().multicast().onBackpressureBuffer()
    private var flux: Flux<OrchestrationRequestContext> = sink.asFlux().publishOn(Schedulers.boundedElastic())

    init {
        orchestrators.forEach { flux.subscribe(it.cancel()) }
    }

    fun cancelOrder(ctx: OrchestrationRequestContext) {
        sink.tryEmitNext(ctx)
    }
}