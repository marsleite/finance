package com.msl.finance.application.routers

import com.msl.finance.application.dto.HttpErrorResponse
import com.msl.finance.application.routers.extensions.TemplateHandler
import com.msl.finance.exception.BaseException
import com.msl.finance.service.Loggable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class ExceptionHandler(
    private val exceptionHandler: Map<Set<String>, TemplateHandler>
): Loggable {

    suspend fun handler(e: Throwable, serverRequest: ServerRequest): ServerResponse {
        log.error("error message ${e.message}")
        val (httpResponse, httpStatus) = when (e) {
            is BaseException -> {
                getExceptionHandler(e)?.handler(e) ?: internalServerError()
            }
            else -> {
                internalServerError()
            }
        }
        return ServerResponse.status(httpStatus).bodyValueAndAwait(httpResponse)
    }

    private suspend fun getExceptionHandler(e: BaseException) =
        filterExceptionByType(e.type).let { exceptionSelected ->
            exceptionSelected.values.firstOrNull()
        }

    private fun filterExceptionByType(type: String) =
        exceptionHandler.filter { (key, _) ->
            key.contains(type)
        }

    private fun internalServerError() = HttpErrorResponse(
        "INTERNAL_SERVER_ERROR",
        "Unknown error"
    ) to HttpStatus.INTERNAL_SERVER_ERROR
}