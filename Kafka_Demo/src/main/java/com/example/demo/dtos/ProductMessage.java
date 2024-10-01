package com.example.demo.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents a message containing product information for Kafka.
 *
 * @param productId   the unique identifier for the product
 * @param productName the name of the product
 * @param price       the price of the product
 * @param available   indicates if the product is currently in stock
 */
@Schema(description = "Data transfer object representing a product message for Kafka")
public record ProductMessage(
    @Schema(
      description = "The unique identifier for the product",
      example = "P12345"
    )
    String productId,

    @Schema(
      description = "The name of the product",
      example = "Sample Product"
    )
    String productName,

    @Schema(
      description = "The price of the product",
      example = "19.99"
    )
    double price,

    @Schema(
      description = "Indicates if the product is currently in stock",
      example = "true"
    )
    boolean available
) {
}
