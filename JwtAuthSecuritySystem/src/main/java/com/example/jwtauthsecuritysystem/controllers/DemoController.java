package com.example.jwtauthsecuritysystem.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for demonstrating secure endpoints.
 */
@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

  @GetMapping("/hello")
  public ResponseEntity<String> sayHello() {
    return ResponseEntity.ok("Hello from secured endpoint!");
  }
}