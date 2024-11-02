package com.example.notificationbot.managers;

import com.example.notificationbot.configurations.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Abstract class for managing interactions in the Telegram bot.
 */
public abstract class AbstractManager {

  public abstract BotApiMethod<?> showMainMenu(Message message, TelegramBot telegramBot);

  public abstract BotApiMethod<?> showMainMenu(CallbackQuery query, TelegramBot telegramBot);
}
