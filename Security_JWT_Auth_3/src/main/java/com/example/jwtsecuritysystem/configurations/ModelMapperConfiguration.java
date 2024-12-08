package com.example.jwtsecuritysystem.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up the ModelMapper bean.
 */
@Configuration
public class ModelMapperConfiguration {

  /**
   * Creates a {@link ModelMapper} bean used for mapping between DTOs and entities.
   *
   * <p>This method initializes and provides a {@link ModelMapper} instance that can be
   * used across the application to map data between different object models, such as
   * between entities and Data Transfer Objects (DTOs).</p>
   *
   * @return A {@link ModelMapper} instance for mapping objects.
   */
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}