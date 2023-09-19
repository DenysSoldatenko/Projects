package com.example.joblisting.configurations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Unit tests for the SpringFoxConfig class.
 */
public class SpringFoxConfigTest {

  @Test
  public void testSwaggerDocketConfiguration() {
    SpringFoxConfig springFoxConfig = new SpringFoxConfig();
    Docket docket = springFoxConfig.api();

    assertEquals(DocumentationType.SWAGGER_2, docket.getDocumentationType());
  }
}
