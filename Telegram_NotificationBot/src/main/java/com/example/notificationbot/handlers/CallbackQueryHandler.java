package com.example.notificationbot.handlers;

import com.example.notificationbot.configurations.TelegramBot;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CallbackQueryHandler extends AbstractHandler {

  @Override
  public BotApiMethod<?> answer(BotApiObject object, TelegramBot telegramBot) {
    CallbackQuery query = (CallbackQuery) object;
    throw new UnsupportedOperationException("Method not implemented yet");
  }
}
