package com.example.weatherbot.handlers;

import com.example.weatherbot.configurations.TelegramBot;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Interface for handling different types of bot API objects.
 */
public interface BaseHandler {
  BotApiMethod<?> handle(BotApiObject object, TelegramBot telegramBot) throws TelegramApiException;
}
