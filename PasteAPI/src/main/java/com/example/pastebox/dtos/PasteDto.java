package com.example.pastebox.dtos;

import com.example.pastebox.models.ExpirationTime;
import com.example.pastebox.models.PublicStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object (DTO) class representing information about a paste.
 */
@Data
@NoArgsConstructor
@Schema(description = "Expiration time options for a paste")
public class PasteDto {

  @Schema(
      description = "Short link for accessing the paste",
      example = "abcd1234"
  )
  private String shortLink;

  @Schema(
      description = "Content of the paste",
      example = "This is an example of paste content"
  )
  private String content;

  @Schema(
      description = "Creation time of the paste",
      example = "2024-08-12T15:30:00"
  )
  private LocalDateTime creationTime;

  @Schema(
      description = "Duration after which the paste expires",
      example = "_10_MINUTES"
  )
  private ExpirationTime expirationDuration;

  @Schema(
      description = "Public visibility status of the paste",
      example = "PUBLIC"
  )
  private PublicStatus publicStatus;
}
