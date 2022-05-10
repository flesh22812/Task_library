package ru.anpilogov.task_library.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new io.swagger.v3.oas.models.info.Info()
                                .title("Library REST")
                                .version("1.0.0")
                                .contact(
                                        new Contact()
                                                .email("ya.anpil90@gmail.com")
                                                .name("Anpilogov Egor"))
                );
    }
}