package com.example.militarytrackerbot.configurations;

import com.example.militarytrackerbot.services.UpdateDispatcher;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Telegram bot configuration that extends the TelegramWebhookBot.
 * This class handles incoming webhook updates and provides bot configuration details.
 */
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class TelegramBot extends TelegramWebhookBot {

  TelegramProperties properties;
  UpdateDispatcher updateDispatcher;

  /**
   * Constructs a new instance of the TelegramBot.
   *
   * @param properties the configuration properties for the Telegram bot, including the token
   * @param updateDispatcher the dispatcher for handling incoming updates
   */
  public TelegramBot(TelegramProperties properties, UpdateDispatcher updateDispatcher) {
    super(properties.getToken());
    this.properties = properties;
    this.updateDispatcher = updateDispatcher;
  }

  @Override
  public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
    return updateDispatcher.processUpdate(update);
  }

  @Override
  public String getBotPath() {
    return properties.getUrl();
  }

  @Override
  public String getBotUsername() {
    return properties.getName();
  }
}
