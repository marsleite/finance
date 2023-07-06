package com.msl.finance.application.handlers

import com.msl.finance.application.routers.ExceptionHandler
import com.msl.finance.application.routers.extensions.BadGatewayHandler
import com.msl.finance.application.routers.extensions.BadRequestHandler
import com.msl.finance.exception.BadRequestException
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

    private val handler = ExceptionHandler(mapOf(
        setOf(TypeException.INVALID_REQUEST.name) to BadRequestHandler(),
        setOf(TypeException.PUBLISHER_ERROR.name) to BadGatewayHandler()
    ))

    private val request = mockk<ServerRequest>()

    @Test
    fun `should handler exception bad request`() {
        val exception = BadRequestException("messages", TypeException.INVALID_REQUEST.name)
        runTest {
            val result = handler.handler(exception, request)
            Assertions.assertThat(result.statusCode().value()).isEqualTo(HttpStatus.BAD_REQUEST.value())
        }
    }

    @Test
    fun `should handler exception bad gateway`() {
        val exception = BadRequestException("messages", TypeException.PUBLISHER_ERROR.name)
        runTest {
            val result = handler.handler(exception, request)
            Assertions.assertThat(result.statusCode().value()).isEqualTo(HttpStatus.BAD_GATEWAY.value())
        }
    }

    @Test
    fun `should handler exception internal server error`() {
        val exception = BadRequestException("message", "INVALID_TYPE")
        runTest {
            val result = handler.handler(exception, request)
            Assertions.assertThat(result.statusCode().value()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value())
        }
    }

    @Test
    fun `should handler exception internal server error when not mapped error`() {
        val exception = IllegalArgumentException("message")
        runTest {
            val result = handler.handler(exception, request)
            Assertions.assertThat(result.statusCode().value()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value())
        }
    }
}