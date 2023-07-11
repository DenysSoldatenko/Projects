package com.example.pastebox.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PasteDto {
  private String content;
  private LocalDateTime creationTime;
  private ExpirationTime expirationDuration;
  private PublicStatus publicStatus;
}
