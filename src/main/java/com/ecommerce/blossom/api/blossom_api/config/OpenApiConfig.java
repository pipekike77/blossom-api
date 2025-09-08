package com.ecommerce.blossom.api.blossom_api.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Blossom E-commerce API")
                        .description("API for managing products, users, orders and payments in the Blossom E-commerce platform.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Blossom Support")
                                .email("support@blossom.net")
                                .url("https://www.blossom.net/"))
                        .license(new License()
                                .name("© 2025 Blossom Financial Technologies LLC."))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Project Documentation")
                        .url("https://github.com/pipekike77/blossom-api"));
    }
}