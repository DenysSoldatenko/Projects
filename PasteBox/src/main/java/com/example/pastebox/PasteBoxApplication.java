package com.example.pastebox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main application class for PasteBox.
 */
@EnableScheduling
@SpringBootApplication
public class PasteBoxApplication {

  public static void main(String[] args) {
    SpringApplication.run(PasteBoxApplication.class, args);
  }
}
