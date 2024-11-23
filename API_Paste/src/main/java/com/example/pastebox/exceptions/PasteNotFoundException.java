package com.example.pastebox.exceptions;

/**
 * Exception thrown when a paste is not found.
 */
public class PasteNotFoundException extends RuntimeException {

  /**
   * Constructor to create a new PasteNotFoundException with a custom error message.
   *
   * @param message The message that describes the reason for the exception.
   */
  public PasteNotFoundException(String message) {
    super(message);
  }
}
