package com.example.joblisting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main class for the JobListing application.
 * This class is responsible for starting the Spring Boot application.
 */
@SpringBootApplication
public class JobListingApiApplication {

  /**
   * The main method that launches the Spring Boot application.
   *
   * @param args Command-line arguments passed to the application (not used in this case).
   */
  public static void main(String[] args) {
    SpringApplication.run(JobListingApiApplication.class, args);
  }
}
