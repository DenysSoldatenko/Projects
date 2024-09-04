package com.example.springsecuritysystem.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling greeting-related requests.
 */
@RestController
@Tag(
    name = "Greeting Controller",
    description = "APIs for greeting messages"
)
public class GreetingController {

  /**
   * Returns a greeting message.
   *
   * @return A greeting message as a String.
   */
  @Operation(
      summary = "Get greeting message",
      description = "Returns a simple greeting message from the API"
  )
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "Successfully returned the greeting message"
        ),
      @ApiResponse(
          responseCode = "403",
          description = "Forbidden: Invalid credentials or access denied"
        )
      }
  )
  @GetMapping("/greetings")
  public String sayHello() {
    return "Hello from API!";
  }
}
