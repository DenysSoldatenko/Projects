package com.example.notificationbot.listeners;

import com.example.notificationbot.configurations.TelegramBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Interface for handling commands in the Telegram bot.
 */
public interface CommandListener {

  BotApiMethod<?> processCommand(Message message, TelegramBot telegramBot);
}