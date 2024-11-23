package com.example.pastebox.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up the ModelMapper bean.
 */
@Configuration
public class ModelMapperConfig {

  /**
   * Bean definition for ModelMapper.
   * This method creates and returns a new instance of ModelMapper.
   * ModelMapper can be used for mapping between various objects (e.g., DTOs and entities)
   * in the Spring context.
   *
   * @return A new instance of ModelMapper.
   */
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}