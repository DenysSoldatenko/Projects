package com.example.notificationbot.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Telegram bot properties.
 * This class holds the bot's URL, name, and token, which are injected from application properties.
 */
@Configuration
public class TelegramConfiguration {

  @Value("${telegram.bot.url}")
  private String url;

  @Value("${telegram.bot.name}")
  private String name;

  @Value("${telegram.bot.token}")
  private String token;
}
