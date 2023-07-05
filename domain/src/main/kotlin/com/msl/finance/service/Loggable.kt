package com.msl.finance.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory

interface Loggable {

    val log: Logger get() = LoggerFactory.getLogger(this.javaClass)
}