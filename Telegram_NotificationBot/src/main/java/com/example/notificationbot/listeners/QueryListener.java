package com.example.notificationbot.listeners;

import com.example.notificationbot.configurations.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Interface for handling callback queries in the Telegram bot.
 */
public interface QueryListener {

  /**
   * Processes a callback query and executes the corresponding action
   * based on the query and the words provided.
   *
   * @param query The {@link CallbackQuery} object containing the callback data from the user.
   * @param words An array of words (likely parsed from the callback data)
   *              that determines the action to take.
   * @param telegramBot The {@link TelegramBot} instance used to send the response.
   * @return A {@link BotApiMethod} representing the response to be sent to the user.
   * @throws TelegramApiException If an error occurs while processing the query
   *                              or sending the response.
   */
  BotApiMethod<?> processQuery(CallbackQuery query, String[] words, TelegramBot telegramBot) throws TelegramApiException;
}
