package com.example.demo.exeptions;

/**
 * Exception thrown when a student is not found.
 */
public class StudentNotFoundException extends RuntimeException {
  public StudentNotFoundException(String message) {
    super(message);
  }
}
