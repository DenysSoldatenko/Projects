package com.example.securitysystem.utils;

import java.util.function.Predicate;

import org.springframework.stereotype.Component;

/**
 * Service class for validating email addresses using a regular expression.
 */
@Component
public class EmailValidator implements Predicate<String> {

  private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";

  @Override
  public boolean test(String email) {
    return email.matches(EMAIL_REGEX);
  }
}