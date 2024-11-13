package com.example.militarytrackerbot.controllers;

import com.example.militarytrackerbot.configurations.TelegramBot;
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
 * Controller that handles user interactions with the Telegram Military Tracker Bot.
 * It provides endpoints for the bot's webhook and a welcome message.
 */
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

  TelegramBot telegramBot;

  /**
   * Displays the home page with a welcome message.
   *
   * @return A string with the welcome message.
   */
  @GetMapping("/")
  public String home() {
    return "Welcome to the Telegram Military Tracker Bot!";
  }

  /**
   * Handles incoming webhook updates from Telegram.
   *
   * @param update The update received from Telegram containing information about the message or callback.
   * @return A BotApiMethod response that is sent back to Telegram based on the update.
   */
  @PostMapping
  public BotApiMethod<?> listener(@RequestBody Update update) {
    return telegramBot.onWebhookUpdateReceived(update);
  }
}
