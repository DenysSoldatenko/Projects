package com.example.springsecuritysystem.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Unit tests for the GreetingController class.
 */
class GreetingControllerTest {

  private GreetingController greetingController;

  @BeforeEach
  public void setUp() {
    greetingController = new GreetingController();
  }

  @Test
  void shouldReturnHello() {
    ResponseEntity<String> response = greetingController.sayHello();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Hello from API!", response.getBody());
  }

  @Test
  void shouldReturnGoodBye() {
    ResponseEntity<String> response = greetingController.sayGoodBye();
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Goodbye and see you later!", response.getBody());
  }
}
