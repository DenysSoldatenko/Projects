package com.example.militarytrackerbot.exceptions;

/**
 * Exception thrown when a message does not contain text.
 */
public class MessageNotTextException extends RuntimeException {
  public MessageNotTextException(String message) {
    super(message);
  }
}
