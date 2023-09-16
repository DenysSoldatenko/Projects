package com.example.securitysystem.registration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the EmailValidator class.
 */
public class EmailValidatorTest {

  private final EmailValidator emailValidator = new EmailValidator();

  @Test
  public void testValidEmails() {
    assertTrue(emailValidator.test("test@example.com"));
    assertTrue(emailValidator.test("john.doe@example.co.uk"));
    assertTrue(emailValidator.test("user1234@example.subdomain.example.org"));
    assertTrue(emailValidator.test("test_email@example.com"));
    assertTrue(emailValidator.test("user.name@example.com"));
  }

  @Test
  public void testInvalidEmails() {
    assertFalse(emailValidator.test("invalidemail@"));
    assertFalse(emailValidator.test("invalidemail.com"));
    assertFalse(emailValidator.test("@example.com"));
  }
}
