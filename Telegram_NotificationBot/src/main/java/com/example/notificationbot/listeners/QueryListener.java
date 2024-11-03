package com.example.notificationbot.listeners;

import com.example.notificationbot.configurations.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Interface for handling callback queries in the Telegram bot.
 */
public interface QueryListener {

  BotApiMethod<?> processQuery(CallbackQuery query, String[] words, TelegramBot telegramBot) throws TelegramApiException;
}
