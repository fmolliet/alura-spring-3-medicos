package io.winty.alura.learningspring.infra.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;

@Configuration
public class SpringDocConfiguration {
    @Bean
     public OpenAPI customOpenAPI() {
         return new OpenAPI()
                        .components(new Components()
                            .addSecuritySchemes("bearer-key",
                            new SecurityScheme().type(Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }
}
