package com.msl.finance.application.handlers

import com.msl.finance.application.routers.ExceptionHandler
import com.msl.finance.application.routers.extensions.BadGatewayHandler
import com.msl.finance.application.routers.extensions.BadRequestHandler
import com.msl.finance.exception.BadRequestException
import com.msl.finance.exception.CommunicationExternalException
import com.msl.finance.exception.TypeException
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.server.ServerRequest

@ExperimentalCoroutinesApi
class ExceptionHandlerTest {

    val handler = ExceptionHandler(mapOf(
        setOf("INVALID_REQUEST") to BadRequestHandler(),
        setOf("PUBLISHER_ERROR") to BadGatewayHandler()
    ))

    val request = mockk<ServerRequest>()

    @Test
    fun `should handler exception bad request`() {
        val exception = BadRequestException("message", "INVALID_REQUEST")
        runTest {
            val result = handler.handler(exception, request)
            Assertions.assertThat(result.rawStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value())
        }
    }

    @Test
    fun `should handler exception internal server error`() {
        val exception = BadRequestException("message", "INVALID_TYPE")
        runTest {
            val result = handler.handler(exception, request)
            Assertions.assertThat(result.rawStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value())
        }
    }

    @Test
    fun `should handler exception internal server error when not mapped error`() {
        val exception = IllegalArgumentException("message")
        runTest {
            val result = handler.handler(exception, request)
            Assertions.assertThat(result.rawStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value())
        }
    }

    @Test
    fun `should handler an exception bad gateway`() {
        val exception = CommunicationExternalException("message", "PUBLISHER_ERROR")
        runTest {
            val result = handler.handler(exception, request)
            Assertions.assertThat(result.rawStatusCode()).isEqualTo(HttpStatus.BAD_GATEWAY.value())
        }
    }
}