package com.example.notificationbot.managers.message;

import com.example.notificationbot.configurations.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Interface for managing interactions with the Telegram bot for messages.
 */
public interface MessageManager {

  /**
   * Manages interactions with the bot for displaying the main menu.
   *
   * @param message The message that triggered the action.
   * @param telegramBot The bot instance used to send the response.
   * @return A BotApiMethod<?> response to display the main menu.
   */
  BotApiMethod<?> showMainMenu(Message message, TelegramBot telegramBot);
}