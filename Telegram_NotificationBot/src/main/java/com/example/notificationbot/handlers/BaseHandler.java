package com.example.notificationbot.handlers;

import com.example.notificationbot.configurations.TelegramBot;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Interface for handling different types of bot API objects.
 */
public interface BaseHandler {

  /**
   * Processes a BotApiObject and returns a response.
   *
   * @param object The BotApiObject to process.
   * @param telegramBot The bot instance for sending responses.
   * @return A BotApiMethod<?> response to send back.
   * @throws TelegramApiException If an error occurs while processing the request.
   */
  BotApiMethod<?> handle(BotApiObject object, TelegramBot telegramBot) throws TelegramApiException;
}
