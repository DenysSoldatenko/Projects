package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class for the Demo application.
 * This class is responsible for starting the Spring Boot application.
 */
@Slf4j
@SpringBootApplication
public class StudentApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(StudentApiApplication.class, args);
  }
}
