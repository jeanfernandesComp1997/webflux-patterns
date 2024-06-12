package com.sample.webfluxpatterns

import com.sample.webfluxpatterns.sec10.dto.ProductAggregate
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.time.Duration
import java.time.LocalDateTime

@SpringBootTest
class BulkHeadTest {

    private val client: WebClient = WebClient.builder()
        .baseUrl("http://localhost:8080/sec10/")
        .build()

    @Test
    fun `concurrent users`() {
        StepVerifier.create(Flux.merge(fibonacciRequests(), productRequests()))
            .verifyComplete()
        StepVerifier.create(productRequests())
            .verifyComplete()
    }

    private fun fibonacciRequests(): Mono<Void> {
        return Flux.range(1, 2)
            .flatMap {
                client.get()
                    .uri("fibonacci/46")
                    .retrieve()
                    .bodyToMono(Long::class.java)
            }
            .doOnNext(::print)
            .then()
    }

    private fun productRequests(): Mono<Void> {
        return Mono.delay(Duration.ofMillis(100))
            .thenMany(Flux.range(1, 2))
            .flatMap {
                client.get()
                    .uri("products/1")
                    .retrieve()
                    .bodyToMono(ProductAggregate::class.java)
            }
            .map { it.category }
            .doOnNext { print(it) }
            .then()
    }

    private fun print(o: Any) {
        println("${LocalDateTime.now()} : $o")
    }
}