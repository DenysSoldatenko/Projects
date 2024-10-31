package com.example.notificationbot.configurations;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Telegram bot implementation that extends the TelegramWebhookBot.
 * This class handles incoming webhook updates and provides bot configuration details.
 */
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class TelegramBot extends TelegramWebhookBot {

  TelegramConfiguration telegramConfiguration;

  public TelegramBot(TelegramConfiguration telegramConfiguration) {
    super(telegramConfiguration.getToken());
    this.telegramConfiguration = telegramConfiguration;
  }

  @Override
  public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
    return null;
  }

  @Override
  public String getBotPath() {
    return telegramConfiguration.getUrl();
  }

  @Override
  public String getBotUsername() {
    return telegramConfiguration.getName();
  }
}
