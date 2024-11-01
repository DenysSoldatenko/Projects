package com.example.notificationbot.handlers;

import com.example.notificationbot.configurations.TelegramBot;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public abstract class AbstractHandler {
  public abstract BotApiMethod<?> answer(BotApiObject object, TelegramBot telegramBot);
}
