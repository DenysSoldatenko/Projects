package com.example.pastebox.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.pastebox.dtos.PasteDto;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the PasteDto class.
 */
class PasteDtoTest {

  @Test
  void testGettersAndSetters() {
    PasteDto pasteDto = new PasteDto();

    pasteDto.setShortLink("abc123");
    pasteDto.setContent("This is a test paste.");
    pasteDto.setCreationTime(LocalDateTime.now());
    pasteDto.setExpirationDuration(ExpirationTime._1_HOUR);
    pasteDto.setPublicStatus(PublicStatus.PUBLIC);

    assertEquals("abc123", pasteDto.getShortLink());
    assertEquals("This is a test paste.", pasteDto.getContent());
    assertNotNull(pasteDto.getCreationTime());
    assertEquals(ExpirationTime._1_HOUR, pasteDto.getExpirationDuration());
    assertEquals(PublicStatus.PUBLIC, pasteDto.getPublicStatus());
  }
}
