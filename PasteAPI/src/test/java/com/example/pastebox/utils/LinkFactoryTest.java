package com.example.pastebox.utils;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the LinkFactory class.
 */
class LinkFactoryTest {

  @Test
  void shouldGenerateLinkUniqueLinks() {
    for (int i = 0; i < 1000; i++) {
      String link1 = LinkFactory.generateLink();
      String link2 = LinkFactory.generateLink();

      assertNotEquals(link1, link2);
    }
  }
}
