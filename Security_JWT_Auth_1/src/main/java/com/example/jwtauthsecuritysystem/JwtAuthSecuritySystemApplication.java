package com.example.jwtauthsecuritysystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the JWT Authentication and Security System.
 */
@SpringBootApplication
public class JwtAuthSecuritySystemApplication {

  /**
   * The main method that launches the Spring Boot application.
   *
   * @param args Command-line arguments passed to the application (not used in this case).
   */
  public static void main(String[] args) {
    SpringApplication.run(JwtAuthSecuritySystemApplication.class, args);
  }
}
