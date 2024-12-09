package com.example.notificationbot.exceptions;

/**
 * Exception thrown when a message does not contain text.
 */
public class MessageNotTextException extends RuntimeException {

  /**
   * Constructs a new {@code MessageNotTextException} with the specified detail message.
   *
   * @param message The detail message which explains the cause of the exception.
   */
  public MessageNotTextException(String message) {
    super(message);
  }
}
