package com.danielqueiroz.libraryshop.api.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenApi(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("RESTful API with Kotlin 1.6.21 and Spring Boot 3.0")
                    .version("v1")
                    .description("API to provide data about a library recommendation")
                    .license(
                        License().name("Apache 2.0")
                    )
            )
    }
}