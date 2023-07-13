package com.example.pastebox.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up the ModelMapper bean.
 */
@Configuration
public class ModelMapperConfig {
  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}