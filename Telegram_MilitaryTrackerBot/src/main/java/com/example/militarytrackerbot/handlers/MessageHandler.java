package com.example.militarytrackerbot.handlers;

import static com.example.militarytrackerbot.factories.MessageFactory.createMessageResponse;
import static com.example.militarytrackerbot.utils.MessageUtils.COMMANDS_ONLY_MESSAGE;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Handler for processing messages received by the Telegram bot.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageHandler {

  /**
   * Processes an incoming message or update.
   *
   * @param object The incoming update or message.
   * @return The response to send back to the user.
   */
  public BotApiMethod<?> handle(BotApiObject object) {
    var message = (Message) object;
    return createMessageResponse(message, COMMANDS_ONLY_MESSAGE);
  }
}
