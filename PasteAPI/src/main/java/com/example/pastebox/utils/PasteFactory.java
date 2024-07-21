package com.example.pastebox.utils;

import static com.example.pastebox.utils.ExpirationTimeGenerator.generateExpirationTime;

import com.example.pastebox.models.Paste;
import com.example.pastebox.models.PasteDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PasteFactory {

  public static Paste generatePaste(PasteDto pasteDto) {
    return Paste.builder()
      .content(pasteDto.getContent())
      .creationTime(pasteDto.getCreationTime())
      .expirationTime(generateExpirationTime(pasteDto.getCreationTime(), pasteDto.getExpirationDuration()))
      .expirationDuration(pasteDto.getExpirationDuration())
      .publicStatus(pasteDto.getPublicStatus())
      .shortLink(LinkGenerator.generate())
      .build();
  }
}
