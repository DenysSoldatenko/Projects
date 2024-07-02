package com.example.joblisting.configurations;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Configuration class for SpringFox Swagger documentation.
 */
@Configuration
public class SpringFoxConfig {

  /**
   * Configures the Docket bean for Swagger API documentation.
   *
   * @return the Docket bean representing the Swagger documentation configuration
   */
  @Bean
  public Docket api() {
    return new Docket(SWAGGER_2)
    .select()
    .apis(RequestHandlerSelectors.any())
    .paths(PathSelectors.any())
    .build();
  }
}