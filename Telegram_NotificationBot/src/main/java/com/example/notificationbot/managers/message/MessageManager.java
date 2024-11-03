package com.example.notificationbot.managers.message;

import com.example.notificationbot.configurations.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Interface for managing interactions with the Telegram bot for messages.
 */
public interface MessageManager {
  BotApiMethod<?> showMainMenu(Message message, TelegramBot telegramBot);
}