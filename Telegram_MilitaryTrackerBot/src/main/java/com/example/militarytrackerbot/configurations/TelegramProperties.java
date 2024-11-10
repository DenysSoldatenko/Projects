package com.example.militarytrackerbot.configurations;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for managing Telegram bot properties.
 */
@Data
@Configuration
public class TelegramProperties {

  @Value("${telegram.bot.url}")
  private String url;

  @Value("${telegram.bot.name}")
  private String name;

  @Value("${telegram.bot.token}")
  private String token;
}
