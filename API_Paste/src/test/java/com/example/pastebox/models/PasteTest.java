package com.example.pastebox.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Paste class.
 */
class PasteTest {

  private Paste paste;

  @BeforeEach
  public void setUp() {
    paste = new Paste();
  }

  @Test
  void testIdSetterGetter() {
    paste.setId(1);
    assertEquals(1, paste.getId());
  }

  @Test
  void testContentSetterGetter() {
    paste.setContent("This is a test paste.");
    assertEquals("This is a test paste.", paste.getContent());
  }

  @Test
  void testCreationTimeSetterGetter() {
    LocalDateTime creationTime = LocalDateTime.now();
    paste.setCreationTime(creationTime);
    assertEquals(creationTime, paste.getCreationTime());
  }

  @Test
  void testExpirationTimeSetterGetter() {
    LocalDateTime expirationTime = LocalDateTime.now().plusHours(1);
    paste.setExpirationTime(expirationTime);
    assertEquals(expirationTime, paste.getExpirationTime());
  }

  @Test
  void testExpirationDurationSetterGetter() {
    paste.setExpirationDuration(ExpirationTime._1_HOUR);
    assertEquals(ExpirationTime._1_HOUR, paste.getExpirationDuration());
  }

  @Test
  void testPublicStatusSetterGetter() {
    paste.setPublicStatus(PublicStatus.PUBLIC);
    assertEquals(PublicStatus.PUBLIC, paste.getPublicStatus());
  }

  @Test
  void testShortLinkSetterGetter() {
    paste.setShortLink("abc123");
    assertEquals("abc123", paste.getShortLink());
  }

  @Test
  void testBuilder() {
    Paste builtPaste = Paste.builder()
        .id(1)
        .content("Test content")
        .creationTime(LocalDateTime.now())
        .expirationTime(LocalDateTime.now().plusHours(1))
        .expirationDuration(ExpirationTime._1_HOUR)
        .publicStatus(PublicStatus.PUBLIC)
        .shortLink("abc123")
        .build();

    assertEquals(1, builtPaste.getId());
    assertEquals("Test content", builtPaste.getContent());
    assertNotNull(builtPaste.getCreationTime());
    assertNotNull(builtPaste.getExpirationTime());
    assertEquals(ExpirationTime._1_HOUR, builtPaste.getExpirationDuration());
    assertEquals(PublicStatus.PUBLIC, builtPaste.getPublicStatus());
    assertEquals("abc123", builtPaste.getShortLink());
  }
}
