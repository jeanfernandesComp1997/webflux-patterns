package com.sample.webfluxpatterns.sec04.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.sample.webfluxpatterns.sec04.dto.OrchestrationRequestContext

class DebugUtil {

    companion object {

        fun print(ctx: OrchestrationRequestContext) {
            val mapper = ObjectMapper()
            println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ctx))
        }
    }
}