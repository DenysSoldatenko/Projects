package com.example.notificationbot.handlers;

import com.example.notificationbot.configurations.TelegramBot;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

/**
 * Abstract base class for handling different types of bot API objects.
 */
public abstract class BaseHandler {
  public abstract BotApiMethod<?> handle(BotApiObject object, TelegramBot telegramBot);
}
