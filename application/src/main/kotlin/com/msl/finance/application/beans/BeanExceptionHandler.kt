package com.msl.finance.application.beans

import com.msl.finance.application.routers.extensions.TemplateHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanExceptionHandler {

    @Bean
    fun createHandlerException(handlerList: List<TemplateHandler>): Map<Set<String>, TemplateHandler> {
        return handlerList.associateBy { it.getTypes() }
    }
}