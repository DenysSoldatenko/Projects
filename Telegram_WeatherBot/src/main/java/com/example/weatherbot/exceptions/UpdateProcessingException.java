package com.example.weatherbot.exceptions;

/**
 * Exception thrown when an error occurs during the processing of a Telegram update.
 */
public class UpdateProcessingException extends RuntimeException {

  /**
   * Constructs a new UpdateProcessingException with the specified detail message and cause.
   * This exception is thrown when there is an error processing a bot update,
   * and it includes both a descriptive message and the underlying cause of the error.
   *
   * @param message the detail message explaining the reason for the exception.
   * @param cause the underlying cause of the exception.
   */
  public UpdateProcessingException(String message, Throwable cause) {
    super(message, cause);
  }
}
