package com.example.demo.exeptions;

/**
 * Exception thrown when an attempt is made to create a student that already exists.
 */
public class StudentAlreadyExistsException extends RuntimeException {
  public StudentAlreadyExistsException(String message) {
    super(message);
  }
}
