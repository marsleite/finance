package com.msl.finance.application.routers.extensions

import com.msl.finance.application.dto.HttpErrorResponse
import com.msl.finance.exception.BaseException
import com.msl.finance.exception.TypeException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component

@Component
class BadGatewayHandler: TemplateHandler() {
    override suspend fun applyHandler(e: BaseException): Pair<HttpErrorResponse, HttpStatus> {
        return HttpErrorResponse(e.type, e.message, e.details) to HttpStatus.BAD_GATEWAY
    }

    override suspend fun isTypeException(type: String): Boolean = getTypes().contains(type)

    override fun getTypes(): Set<String> = setOf(
        TypeException.PUBLISHER_ERROR.name
    )
}