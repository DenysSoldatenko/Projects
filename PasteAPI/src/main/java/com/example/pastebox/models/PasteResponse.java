package com.example.pastebox.models;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * A record representing the response of a paste operation, including a short link.
 */
@Schema(description = "Response object containing the short link for the paste")
public record PasteResponse(

    @Schema(
      description = "The short link for accessing the paste",
      example = "abcd1234"
    )
    String shortLink
) {
}