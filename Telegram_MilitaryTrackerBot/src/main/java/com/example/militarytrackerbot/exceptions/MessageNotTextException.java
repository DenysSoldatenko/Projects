package com.example.militarytrackerbot.exceptions;

/**
 * Exception thrown when a message does not contain text.
 */
public class MessageNotTextException extends RuntimeException {
  /**
   * Constructs a new MessageNotTextException with the specified detail message.
   *
   * @param message The detail message, which is saved for later retrieval by the {@link Throwable#getMessage()} method.
   */
  public MessageNotTextException(String message) {
    super(message);
  }
}
