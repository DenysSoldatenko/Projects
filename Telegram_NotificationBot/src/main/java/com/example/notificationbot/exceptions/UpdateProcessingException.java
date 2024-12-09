package com.example.notificationbot.exceptions;

/**
 * Exception thrown when an error occurs during the processing of a Telegram update.
 */
public class UpdateProcessingException extends RuntimeException {

  /**
   * Constructs a new exception with the specified detail message and cause.
   *
   * @param message The detail message, which is saved for later retrieval
   *                by the {@link #getMessage()} method.
   * @param cause   The cause of the exception, which is saved for later retrieval
   *                by the {@link #getCause()} method.
   */
  public UpdateProcessingException(String message, Throwable cause) {
    super(message, cause);
  }
}
