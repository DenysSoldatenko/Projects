package com.example.pastebox.models;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object (DTO) class representing information about a paste.
 */
@Data
@NoArgsConstructor
public class PasteDto {
  private String shortLink;
  private String content;
  private LocalDateTime creationTime;
  private ExpirationTime expirationDuration;
  private PublicStatus publicStatus;
}
