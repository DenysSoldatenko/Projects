package com.example.militarytrackerbot.managers;

import static com.example.militarytrackerbot.utils.MessageUtils.WELCOME_MESSAGE_TEMPLATE;

import com.example.militarytrackerbot.factories.MilitaryDataKeyboardFactory;
import com.example.militarytrackerbot.factories.NotificationMessageFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Main manager for processing commands and queries in the Telegram bot.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainManager {

  MilitaryDataKeyboardFactory militaryDataKeyboardFactory;
  NotificationMessageFactory notificationMessageFactory;

  /**
   * Processes a regular command (message) from the user
   * and sends a response with a welcome message and available options.
   *
   * @param message The incoming message from the user.
   * @return A {@link BotApiMethod} that represents the response to be sent to the user.
   */
  public BotApiMethod<?> processCommand(Message message) {
    return notificationMessageFactory.createMessageResponse(
      message,
      WELCOME_MESSAGE_TEMPLATE,
      militaryDataKeyboardFactory.createOptionsMarkup()
    );
  }

  /**
   * Processes a callback query from the user
   * (e.g., after clicking a button in the bot's inline keyboard)
   * and sends a response with a welcome message and available options.
   *
   * @param query The callback query received from the user.
   * @return A {@link BotApiMethod} that represents the response to be sent to the user.
   */
  public BotApiMethod<?> processQuery(CallbackQuery query) {
    return notificationMessageFactory.createEditMessageResponse(
      query,
      WELCOME_MESSAGE_TEMPLATE,
      militaryDataKeyboardFactory.createOptionsMarkup()
    );
  }
}
