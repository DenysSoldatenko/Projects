package com.example.demo.configurations;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Unit tests for the SpringFoxConfig class.
 */
@SpringBootTest
class SpringFoxConfigTest {

  @Autowired
  private Docket docket;

  @Test
  void shouldProvideApiDocketBean() {
    assertThat(docket).isNotNull();
  }
}
