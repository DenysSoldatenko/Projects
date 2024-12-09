package com.example.notificationbot.controllers;

import com.example.notificationbot.configurations.TelegramBot;
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
 * Controller that handles user interactions with the Telegram Notification Bot.
 * It provides endpoints for the bot's webhook and a welcome message.
 */
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

  TelegramBot telegramBot;

  /**
   * Endpoint to display a welcome message for the Telegram Notification Bot.
   *
   * @return A welcome message string.
   */
  @GetMapping("/")
  public String home() {
    return "Welcome to the Telegram Notification Bot!";
  }

  /**
   * Endpoint to process incoming webhook updates and pass them to the bot.
   *
   * @param update The update object containing the incoming data from Telegram.
   * @return A response in the form of a BotApiMethod<?> to be sent back to Telegram.
   */
  @PostMapping
  public BotApiMethod<?> listener(@RequestBody Update update) {
    return telegramBot.onWebhookUpdateReceived(update);
  }
}