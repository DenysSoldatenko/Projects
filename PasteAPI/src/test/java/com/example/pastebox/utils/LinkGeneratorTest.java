package com.example.pastebox.utils;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the LinkGenerator class.
 */
class LinkGeneratorTest {

  @Test
  void shouldGenerateUniqueLinks() {
    for (int i = 0; i < 1000; i++) {
      String link1 = LinkGenerator.generate();
      String link2 = LinkGenerator.generate();

      assertNotEquals(link1, link2);
    }
  }
}
