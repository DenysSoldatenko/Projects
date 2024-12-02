package com.example.springsecuritysystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the Spring Security System application.
 */
@SpringBootApplication
public class SpringSecuritySystemApplication {

  /**
   * The main method that launches the Spring Boot application.
   *
   * @param args Command-line arguments passed to the application (not used in this case).
   */
  public static void main(String[] args) {
    SpringApplication.run(SpringSecuritySystemApplication.class, args);
  }
}
