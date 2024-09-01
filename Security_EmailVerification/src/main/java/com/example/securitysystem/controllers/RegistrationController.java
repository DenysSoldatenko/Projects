package com.example.securitysystem.controllers;

import com.example.securitysystem.dtos.RegistrationRequest;
import com.example.securitysystem.services.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
    name = "Registration Controller",
    description = "Operations related to user registration and account verification"
)
@RequestMapping(path = "api/v1/registration")
public class RegistrationController {

  private final RegistrationService registrationService;

  @GetMapping("/greeting")
  @Operation(
      summary = "Greeting message",
      description = "Returns a greeting message for the user"
  )
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Greeting message returned successfully")
  })
  public String greeting() {
    return "Hello user!";
  }

  @PostMapping
  @Operation(
      summary = "Register a new user",
      description = "Registers a new user with the provided registration details"
  )
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "User registration successful"),
    @ApiResponse(responseCode = "400", description = "Invalid registration request")
  })
  public String register(
      @RequestBody @Parameter(description = "Registration request payload containing user details")
      RegistrationRequest request
  ) {
    return registrationService.register(request);
  }

  @GetMapping("/verify")
  @Operation(
      summary = "Verify user registration token",
      description = "Confirms the user registration using the provided token"
  )
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Token confirmation successful"),
    @ApiResponse(responseCode = "400", description = "Invalid token or token not found")
  })
  public String verify(
      @RequestParam @Parameter(description = "Token used for verifying the user registration")
      String token
  ) {
    return registrationService.confirmToken(token);
  }
}