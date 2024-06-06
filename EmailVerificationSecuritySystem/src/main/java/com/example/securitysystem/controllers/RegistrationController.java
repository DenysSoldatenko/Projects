package com.example.securitysystem.controllers;

import com.example.securitysystem.dtos.RegistrationRequest;
import com.example.securitysystem.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling user registration and confirmation.
 */
@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/registration")
public class RegistrationController {

  private final RegistrationService registrationService;

  @GetMapping("/greeting")
  public String greeting() {
    return "Hello user!";
  }

  @PostMapping
  public String register(@RequestBody RegistrationRequest request) {
    return registrationService.register(request);
  }

  @GetMapping("/verify")
  public String verify(@RequestParam("token") String token) {
    return registrationService.confirmToken(token);
  }
}