package com.example.demo.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A data transfer object representing a message to be sent.
 *
 * @param message the content of the message
 */
@Schema(description = "Data transfer object for sending a message")
public record MessageRequest(
    @Schema(
      description = "The content of the message",
      example = "Hello, World!"
    )
    String message
) {
}
