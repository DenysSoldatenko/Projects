package com.example.jwtsecuritysystem.configurations;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit tests for the ModelMapperConfig class.
 */
@SpringBootTest
class ModelMapperConfigurationTest {

  @Autowired
  private ModelMapper modelMapper;

  @Test
  void shouldProvideModelMapperBean() {
    assertThat(modelMapper).isNotNull();
  }
}
