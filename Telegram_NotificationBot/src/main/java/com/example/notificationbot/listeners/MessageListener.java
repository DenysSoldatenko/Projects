package com.example.notificationbot.listeners;

import com.example.notificationbot.configurations.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Interface for handling messages in the Telegram bot.
 */
public interface MessageListener {

  BotApiMethod<?> processMessage(Message message, TelegramBot telegramBot);
}