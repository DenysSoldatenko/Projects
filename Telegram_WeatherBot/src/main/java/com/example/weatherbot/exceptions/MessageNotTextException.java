package com.example.weatherbot.exceptions;

/**
 * Exception thrown when a message does not contain text.
 */
public class MessageNotTextException extends RuntimeException {

  /**
   * Constructs a new MessageNotTextException with the specified detail message.
   * This exception is thrown when a message is expected to be text, but the
   * content is not of type text.
   *
   * @param message the detail message which explains the reason for the exception.
   */
  public MessageNotTextException(String message) {
    super(message);
  }
}
