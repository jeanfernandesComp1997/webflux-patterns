package com.sample.webfluxpatterns.sec03.util

import com.fasterxml.jackson.databind.ObjectMapper
import com.sample.webfluxpatterns.sec03.dto.OrchestrationRequestContext

class DebugUtil {

    companion object {

        fun print(ctx: OrchestrationRequestContext) {
            val mapper = ObjectMapper()
            println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(ctx))
        }
    }
}