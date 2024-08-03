package com.example.springdoc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
			.info(new Info().title("springdoc").description("Spring Boot REST API documentation.").version("1.0").contact(new Contact().name("Teguh Arief").email("teguh.arifudin@gmail.com").url("https://teguharief.com/")).license(new License().name("Licence")))
			.addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
			.components (new Components().addSecuritySchemes ("bearerAuth", new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
			.externalDocs(new ExternalDocumentation().url("https://teguharief.com/").description("Teguh Arief"));					
	}

}
