package com.example.notificationbot.managers.query;

import com.example.notificationbot.configurations.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

/**
 * Interface for managing interactions with the Telegram bot for callback queries.
 */
public interface QueryManager {

  /**
   * Displays the main menu in response to a callback query.
   *
   * @param query The {@link CallbackQuery} object containing the callback data from the user.
   * @param telegramBot The {@link TelegramBot} instance used to send the response.
   * @return A {@link BotApiMethod} representing the response to display the main menu.
   */
  BotApiMethod<?> showMainMenu(CallbackQuery query, TelegramBot telegramBot);
}