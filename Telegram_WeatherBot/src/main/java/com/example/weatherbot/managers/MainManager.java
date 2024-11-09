package com.example.weatherbot.managers;

import static com.example.weatherbot.utils.MessageUtils.WELCOME_MESSAGE_TEMPLATE;

import com.example.weatherbot.factories.MessageFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Main manager for processing commands and queries in the Telegram bot.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainManager {

  MessageFactory messageFactory;

  public BotApiMethod<?> processCommand(Message message) {
    return messageFactory.createMessageResponse(message.getChatId(), WELCOME_MESSAGE_TEMPLATE);
  }
}