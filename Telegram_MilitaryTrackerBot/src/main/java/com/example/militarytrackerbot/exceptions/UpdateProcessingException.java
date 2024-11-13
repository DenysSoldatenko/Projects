package com.example.militarytrackerbot.exceptions;

/**
 * Exception thrown when an error occurs during the processing of a Telegram update.
 */
public class UpdateProcessingException extends RuntimeException {
  /**
   * Constructs a new {@link UpdateProcessingException} with the specified message and cause.
   *
   * @param message The error message.
   * @param cause   The cause of the exception.
   */
  public UpdateProcessingException(String message, Throwable cause) {
    super(message, cause);
  }
}
