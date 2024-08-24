package com.example.springsecuritysystem.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling greeting-related requests.
 */
@RestController
@RequestMapping("/api/v1/greetings")
public class GreetingController {

  @GetMapping()
  public String sayHello() {
    return "Hello from API!";
  }
}
