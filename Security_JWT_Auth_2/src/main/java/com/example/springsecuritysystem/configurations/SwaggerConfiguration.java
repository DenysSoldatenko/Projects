package com.example.springsecuritysystem.configurations;

import static io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP;

import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Swagger/OpenAPI documentation.
 */
@Configuration
@SecurityScheme(
    name = "Bearer Authentication",
    type = HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
public class SwaggerConfiguration {

  /**
   * Configures OpenAPI documentation with
   * security requirements for Bearer Authentication.
   *
   * @return The OpenAPI instance.
   */
  @Bean
  public OpenAPI openApi() {
    return new OpenAPI().addSecurityItem(
      new SecurityRequirement().addList("Bearer Authentication")
    );
  }
}