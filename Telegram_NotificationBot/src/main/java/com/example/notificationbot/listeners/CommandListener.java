package com.example.notificationbot.listeners;

import com.example.notificationbot.configurations.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Interface for handling commands in the Telegram bot.
 */
public interface CommandListener {

  /**
   * Processes a command from a message and returns a response.
   *
   * @param message The message containing the command.
   * @param telegramBot The bot instance used to send a response.
   * @return A BotApiMethod<?> response to be sent back to the user.
   */
  BotApiMethod<?> processCommand(Message message, TelegramBot telegramBot);
}