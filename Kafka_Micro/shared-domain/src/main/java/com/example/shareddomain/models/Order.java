package com.example.shareddomain.models;

/**
 * Represents an order with details such as order ID, item name, quantity, and total price.
 */
public record Order(
    String id,
    String itemName,
    int quantity,
    double totalPrice
) {
}
