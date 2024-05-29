package com.example.accountapi.models;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents an amount of money.
 */
@Schema(description = "Represents an amount of money")
public record Amount(
    @Schema(description = "Total amount", example = "1000.50")
    float total
) {
}
