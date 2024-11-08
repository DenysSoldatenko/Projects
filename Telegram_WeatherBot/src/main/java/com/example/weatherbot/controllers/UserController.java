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

  @GetMapping("/")
  public String home() {
    return "Welcome to the Telegram Weather Bot!";
  }

  @PostMapping
  public BotApiMethod<?> listener(@RequestBody Update update) {
    return telegramBot.onWebhookUpdateReceived(update);
  }
}
