package com.example.shareddomain.models;

/**
 * Represents an event related to an order,
 * including a message, status, and the associated order details.
 */
public record OrderEvent(
    String message,
    String status,
    Order orderDetails
) {
}
