package com.example.shareddomain.models;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents an event related to an order,
 * including a message, status, and the associated order details.
 */
@Schema(description = "Represents an event related to an order")
public record OrderEvent(
    @Schema(
      description = "Event message",
      example = "Order placed successfully"
    )
    String message,

    @Schema(
      description = "Current status of the order",
      example = "PLACED"
    )
    String status,

    @Schema(
      description = "Associated order details"
    )
    Order orderDetails
) {
}
