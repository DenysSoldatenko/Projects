package com.example.militarytrackerbot.services;

/**
 * A functional interface that defines the contract for formatting responses of type {@code T}.
 * This interface is typically used in scenarios where the response from an external service or API
 * needs to be transformed into a specific string format for display or processing.
 *
 * @param <T> The type of the response to be formatted.
 */
@FunctionalInterface
public interface FormatterFunction<T> {
  String format(T response);
}