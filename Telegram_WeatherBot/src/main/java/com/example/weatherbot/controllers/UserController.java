package com.example.weatherbot.controllers;

import com.example.weatherbot.configurations.TelegramBot;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Controller that handles user interactions with the Telegram Weather Bot.
 * It provides endpoints for the bot's webhook and a welcome message.
 */
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

  TelegramBot telegramBot;

  /**
   * Handles GET requests to the root URL ("/").
   * Returns a welcome message for the Telegram Weather Bot.
   *
   * @return a string containing the welcome message.
   */
  @GetMapping("/")
  public String home() {
    return "Welcome to the Telegram Weather Bot!";
  }

  /**
   * Handles POST requests to the webhook endpoint.
   * Receives an Update object, which represents the update from Telegram,
   * and processes it using the bot's webhook handler.
   *
   * @param update the incoming update containing data from Telegram.
   * @return a BotApiMethod containing the response to be sent to Telegram.
   */
  @PostMapping
  public BotApiMethod<?> listener(@RequestBody Update update) {
    return telegramBot.onWebhookUpdateReceived(update);
  }
}
