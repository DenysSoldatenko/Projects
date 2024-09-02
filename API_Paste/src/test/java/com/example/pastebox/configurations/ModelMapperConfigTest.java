package com.example.pastebox.configurations;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit tests for the ModelMapperConfig class.
 */
@SpringBootTest(classes = ModelMapperConfig.class)
class ModelMapperConfigTest {

  @Autowired
  private ModelMapper modelMapper;

  @Test
  void testModelMapperBean() {
    assertNotNull(modelMapper);
  }
}
