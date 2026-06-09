package com.taskforge.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI taskForgeOpenAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("TaskForge API")
                                .description("TaskForge Issue Tracking System APIs")
                                .version("1.0")
                                .contact(
                                        new Contact()
                                                .name("Jitendra Singh")
                                                .email("jitendra@gmail.com")
                                )
                );
    }
}