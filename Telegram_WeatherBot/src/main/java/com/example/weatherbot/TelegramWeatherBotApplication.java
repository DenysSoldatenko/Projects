package com.example.weatherbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the TelegramWeatherBot application.
 */
@SpringBootApplication
public class TelegramWeatherBotApplication {

  /**
   * The main method that launches the Spring Boot application.
   *
   * @param args Command-line arguments passed to the application (not used in this case).
   */
  public static void main(String[] args) {
    SpringApplication.run(TelegramWeatherBotApplication.class, args);
  }

}
