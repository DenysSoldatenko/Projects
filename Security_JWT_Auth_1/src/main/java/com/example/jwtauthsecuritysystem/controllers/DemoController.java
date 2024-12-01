package com.example.jwtauthsecuritysystem.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for demonstrating secure endpoints. This controller includes a secured
 * endpoint that returns a "Hello" message.
 */
@RestController
@RequestMapping("/api/v1/demo-controller")
@Tag(name = "Demo Controller", description = "Controller for demonstrating secure endpoints")
public class DemoController {

  /**
   * Returns a hello message from a secured endpoint.
   *
   * @return A hello message if the request is successful.
   */
  @GetMapping("/hello")
  @Operation(
      summary = "Say Hello",
      description = "Returns a hello message from a secured endpoint",
      responses = {
          @ApiResponse(
            responseCode = "200",
            description = "Successful operation",
            content = @Content(mediaType = "text/plain")
          ),
          @ApiResponse(
            responseCode = "403",
            description = "Forbidden: Access is denied due to insufficient permissions",
            content = @Content(mediaType = "text/plain")
          )
      }
  )
  public String sayHello() {
    return "Hello from secured endpoint!";
  }
}
