package com.example.pastebox.exceptions;

/**
 * Exception thrown when a paste is not found.
 */
public class PasteNotFoundException extends RuntimeException {
  public PasteNotFoundException(String message) {
    super(message);
  }
}
