package com.example.pastebox.models;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PasteDto {
  private String shortLink;
  private String content;
  private LocalDateTime creationTime;
  private ExpirationTime expirationDuration;
  private PublicStatus publicStatus;
}
