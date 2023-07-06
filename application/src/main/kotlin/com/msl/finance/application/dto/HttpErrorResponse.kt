package com.msl.finance.application.dto

data class HttpErrorResponse(
    val type: String,
    val message: String,
    val details: Map<String, String> = mapOf()
)