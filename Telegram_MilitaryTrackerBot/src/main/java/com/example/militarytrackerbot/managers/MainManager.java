package com.example.militarytrackerbot.managers;

import static com.example.militarytrackerbot.factories.MessageFactory.createEditMessageResponse;
import static com.example.militarytrackerbot.factories.MessageFactory.createMessageResponse;
import static com.example.militarytrackerbot.utils.MessageUtils.AVAILABLE_COMMANDS_MESSAGE;
import static com.example.militarytrackerbot.utils.MessageUtils.WELCOME_MESSAGE_TEMPLATE;

import com.example.militarytrackerbot.factories.DataKeyboardFactory;
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

  DataKeyboardFactory dataKeyboardFactory;

  /**
   * Displays the main menu to the user in response to a regular command (message).
   * Sends a welcome message along with a set of available options to the user.
   *
   * @param message The incoming message from the user that triggers the display of the main menu.
   * @return A {@link BotApiMethod} that represents the response to be sent to the user,
   *     containing the welcome message and options markup.
   */
  public BotApiMethod<?> showMainMenu(Message message) {
    return createMessageResponse(
      message,
      WELCOME_MESSAGE_TEMPLATE,
      dataKeyboardFactory.createOptionsMarkup()
    );
  }

  /**
   * Displays the main menu to the user with a welcome message
   * and a set of primary options.
   *
   * @param query The callback query received from the user.
   * @return A {@link BotApiMethod} that represents the response to be sent to the user.
   */
  public BotApiMethod<?> showMainMenu(CallbackQuery query) {
    return createEditMessageResponse(
      query,
      WELCOME_MESSAGE_TEMPLATE,
      dataKeyboardFactory.createOptionsMarkup()
    );
  }

  /**
   * Displays the available commands to the user with instructions
   * on how to proceed, along with a set of main options.
   *
   * @param query The callback query received from the user.
   * @return A {@link BotApiMethod} that represents the response to be sent to the user.
   */
  public BotApiMethod<?> showAvailableCommands(CallbackQuery query) {
    return createEditMessageResponse(
      query,
      AVAILABLE_COMMANDS_MESSAGE,
      dataKeyboardFactory.createMainOptionsMarkup()
    );
  }
}
