package com.example.demo.dtos;

/**
 * Represents a message containing product information for Kafka.
 *
 * @param productId   the unique identifier for the product
 * @param productName the name of the product
 * @param price       the price of the product
 * @param available   indicates if the product is currently in stock
 */
public record ProductMessage(
    String productId,
    String productName,
    double price,
    boolean available
) {
}
