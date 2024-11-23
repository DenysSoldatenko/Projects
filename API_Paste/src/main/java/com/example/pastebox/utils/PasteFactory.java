package com.example.pastebox.utils;

import com.example.pastebox.dtos.PasteDto;
import com.example.pastebox.models.Paste;
import lombok.experimental.UtilityClass;

/**
 * Utility class for creating {@link Paste} objects.
 * Provides a method to generate a {@link Paste} instance from a {@link PasteDto} object.
 */
@UtilityClass
public class PasteFactory {

  /**
   * Generates a {@link Paste} instance based on the provided {@link PasteDto}.
   * The expiration time and short link are automatically generated.
   *
   * @param pasteDto The {@link PasteDto} object containing the details for creating a {@link Paste}.
   * @return A new {@link Paste} instance with the provided content, creation time, expiration time,
   *         expiration duration, public status, and a generated short link.
   */
  public static Paste generatePaste(PasteDto pasteDto) {
    return Paste.builder()
      .content(pasteDto.getContent())
      .creationTime(pasteDto.getCreationTime())
      .expirationTime(
        ExpirationTimeFactory.generateExpirationTime(
          pasteDto.getCreationTime(), pasteDto.getExpirationDuration()
        )
      )
      .expirationDuration(pasteDto.getExpirationDuration())
      .publicStatus(pasteDto.getPublicStatus())
      .shortLink(LinkFactory.generateLink())
      .build();
  }
}
