package com.example.pastebox.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.pastebox.dtos.PasteResponse;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the PasteResponse class.
 */
class PasteResponseTest {

  @Test
  void testConstructorAndGetters() {
    PasteResponse pasteResponse = new PasteResponse("abc123");

    assertEquals("abc123", pasteResponse.shortLink());
  }

  @Test
  void testToString() {
    PasteResponse pasteResponse = new PasteResponse("abc123");

    assertEquals("PasteResponse[shortLink=abc123]", pasteResponse.toString());
  }
}
