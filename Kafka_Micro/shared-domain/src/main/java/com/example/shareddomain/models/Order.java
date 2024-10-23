package com.example.shareddomain.models;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents an order with details such as order ID, item name, quantity, and total price.
 */
@Schema(description = "Represents an order with essential details")
public record Order(
    @Schema(
      description = "Unique order identifier",
      example = "12345"
    )
    String id,

    @Schema(
      description = "Name of the item",
      example = "Laptop"
    )
    String itemName,

    @Schema(
      description = "Quantity ordered",
      example = "2"
    )
    int quantity,

    @Schema(
      description = "Total order price",
      example = "1999.99"
    )
    double totalPrice
) {
}
