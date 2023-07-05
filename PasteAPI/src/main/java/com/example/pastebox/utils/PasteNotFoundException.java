package com.example.pastebox.utils;

/**
 * Exception thrown when a paste is not found.
 */
public class PasteNotFoundException extends RuntimeException {
  public PasteNotFoundException(String message) {
    super(message);
  }
}
