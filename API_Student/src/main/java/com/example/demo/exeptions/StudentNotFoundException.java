package com.example.demo.exeptions;

/**
 * Exception thrown when a student is not found.
 */
public class StudentNotFoundException extends RuntimeException {

  /**
   * Constructor to create a new StudentNotFoundException with a custom error message.
   *
   * @param message The message describing the reason for the exception.
   */
  public StudentNotFoundException(String message) {
    super(message);
  }
}
