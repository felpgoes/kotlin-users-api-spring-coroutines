package com.study.coroutines_crud.config

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Component
class JacksonConfig {
    @Bean
    @Primary
    fun objectMapper(): ObjectMapper {
        return jacksonObjectMapper()
            .registerModules(KotlinModule.Builder().build())
            .registerModules(JavaTimeModule())
            .setSerializationInclusion(Include.NON_NULL)
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    }
}
