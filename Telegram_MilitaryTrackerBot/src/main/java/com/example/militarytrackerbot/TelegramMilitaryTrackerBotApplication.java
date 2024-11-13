package com.example.militarytrackerbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the TelegramMilitaryTrackerBot application.
 */
@SpringBootApplication
public class TelegramMilitaryTrackerBotApplication {

  /**
   * The main method that launches the Spring Boot application.
   *
   * @param args Command-line arguments passed to the application (not used in this case).
   */
  public static void main(String[] args) {
    SpringApplication.run(TelegramMilitaryTrackerBotApplication.class, args);
  }

}
