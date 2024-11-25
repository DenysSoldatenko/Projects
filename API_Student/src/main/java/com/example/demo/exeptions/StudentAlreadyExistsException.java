package com.example.demo.exeptions;

/**
 * Exception thrown when an attempt is made to create a student that already exists.
 */
public class StudentAlreadyExistsException extends RuntimeException {

  /**
   * Constructor to create a new StudentAlreadyExistsException with a custom error message.
   *
   * @param message The message describing the reason for the exception.
   */
  public StudentAlreadyExistsException(String message) {
    super(message);
  }
}
