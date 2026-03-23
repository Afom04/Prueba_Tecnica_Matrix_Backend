package com.matrix.technicaltest.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Technical Test API - Matrix")
                        .version("1.0")
                        .description("API Documentation for the Technical Test Backend - Request and Resource Management")
                        .contact(new Contact()
                                .name("Matrix Team")
                                .email("contact@matrix.com")));
    }
}
