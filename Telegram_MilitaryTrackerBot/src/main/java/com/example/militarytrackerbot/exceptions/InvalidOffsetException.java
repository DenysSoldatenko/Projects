package com.example.militarytrackerbot.exceptions;

/**
 * Custom exception to handle errors related to invalid offset values.
 * This exception is thrown when an invalid offset (less than 0) is encountered during pagination.
 */
public class InvalidOffsetException extends RuntimeException {
  public InvalidOffsetException(String message) {
    super(message);
  }
}
