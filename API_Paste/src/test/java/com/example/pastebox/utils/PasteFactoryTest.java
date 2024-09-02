package com.example.pastebox.utils;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.pastebox.dtos.PasteDto;
import com.example.pastebox.models.ExpirationTime;
import com.example.pastebox.models.Paste;
import com.example.pastebox.models.PublicStatus;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the PasteFactory class.
 */
class PasteFactoryTest {

  @Test
  void generatePaste_createsPasteFromPasteDto() {
    LocalDateTime creationTime = LocalDateTime.now();
    PasteDto pasteDto = new PasteDto();
    pasteDto.setContent("Sample content");
    pasteDto.setCreationTime(creationTime);
    pasteDto.setExpirationDuration(ExpirationTime._1_DAY);
    pasteDto.setPublicStatus(PublicStatus.PUBLIC);
    Paste paste = PasteFactory.generatePaste(pasteDto);

    assertThat(paste).isNotNull();
    assertThat(paste.getContent()).isEqualTo(pasteDto.getContent());
    assertThat(paste.getCreationTime()).isEqualTo(pasteDto.getCreationTime());
    assertThat(paste.getExpirationDuration()).isEqualTo(pasteDto.getExpirationDuration());
    assertThat(paste.getPublicStatus()).isEqualTo(pasteDto.getPublicStatus());
  }
}
