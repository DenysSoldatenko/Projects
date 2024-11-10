package com.example.militarytrackerbot.exceptions;

/**
 * Exception thrown when an error occurs during the processing of a Telegram update.
 */
public class UpdateProcessingException extends RuntimeException {
  public UpdateProcessingException(String message, Throwable cause) {
    super(message, cause);
  }
}
