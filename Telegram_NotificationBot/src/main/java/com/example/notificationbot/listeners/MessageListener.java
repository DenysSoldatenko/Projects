package com.example.notificationbot.listeners;

import com.example.notificationbot.configurations.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Interface for handling messages in the Telegram bot.
 */
public interface MessageListener {

  /**
   * Processes a message and returns a response.
   *
   * @param message The message to process.
   * @param telegramBot The bot instance used to send a response.
   * @return A BotApiMethod<?> response to send back to the user.
   * @throws TelegramApiException If an error occurs while processing the message.
   */
  BotApiMethod<?> processMessage(Message message, TelegramBot telegramBot) throws TelegramApiException;
}