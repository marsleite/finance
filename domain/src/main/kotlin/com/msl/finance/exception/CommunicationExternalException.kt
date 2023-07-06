package com.msl.finance.exception

class CommunicationExternalException(
    override val message: String,
    override val type: String,
    override val details: Map<String, String> = emptyMap(),
    val throwable: Throwable? = null
): BaseException(message, type, details, throwable)