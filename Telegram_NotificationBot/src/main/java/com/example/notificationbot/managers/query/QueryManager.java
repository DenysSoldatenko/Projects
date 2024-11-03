package com.example.notificationbot.managers.query;

import com.example.notificationbot.configurations.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

/**
 * Interface for managing interactions with the Telegram bot for callback queries.
 */
public interface QueryManager {
  BotApiMethod<?> showMainMenu(CallbackQuery query, TelegramBot telegramBot);
}