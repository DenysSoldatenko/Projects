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
public class ModelMapperConfigTest {

  @Autowired
  private ModelMapper modelMapper;

  @Test
  public void shouldProvideModelMapperBean() {
    assertThat(modelMapper).isNotNull();
  }
}
