package com.msl.finance.application.routers

import com.msl.finance.application.handlers.PingHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.coRouter

@Component
class RouterPing {

    @Bean
    fun ping(pingHandler: PingHandler) = coRouter {
        GET("/ping", pingHandler::getPong)
    }
}