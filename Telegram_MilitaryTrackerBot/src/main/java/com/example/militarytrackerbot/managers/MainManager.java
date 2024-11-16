package com.example.militarytrackerbot.managers;

import static com.example.militarytrackerbot.factories.MessageFactory.createEditMessageResponse;
import static com.example.militarytrackerbot.factories.MessageFactory.createMessageResponse;
import static com.example.militarytrackerbot.utils.MessageUtils.AVAILABLE_COMMANDS_MESSAGE;
import static com.example.militarytrackerbot.utils.MessageUtils.HELP_MESSAGE;
import static com.example.militarytrackerbot.utils.MessageUtils.INVALID_INPUT_MESSAGE;
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
 * Manager class for processing user commands and sending appropriate responses.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainManager {

  DataKeyboardFactory dataKeyboardFactory;

  /**
   * Sends the main menu to the user in response to a message.
   *
   * @param message The user's message that triggers the display of the main menu.
   * @return A BotApiMethod that contains the main menu and options for the user.
   */
  public BotApiMethod<?> showMainMenu(Message message) {
    return createMessageResponse(
      message,
      WELCOME_MESSAGE_TEMPLATE,
      dataKeyboardFactory.createOptionsMarkup()
    );
  }

  /**
   * Sends the main menu to the user in response to a callback query.
   *
   * @param query The callback query received from the user.
   * @return A BotApiMethod that contains the main menu and options for the user.
   */
  public BotApiMethod<?> showMainMenu(CallbackQuery query) {
    return createEditMessageResponse(
      query,
      WELCOME_MESSAGE_TEMPLATE,
      dataKeyboardFactory.createOptionsMarkup()
    );
  }

  /**
   * Sends a help message to the user in response to a help command.
   *
   * @param message The user's message requesting help.
   * @return A BotApiMethod that contains the help message for the user.
   */
  public BotApiMethod<?> showHelpMessage(Message message) {
    return createMessageResponse(message, HELP_MESSAGE);
  }

  /**
   * Sends an invalid input message to the user.
   *
   * @param message The user's message that triggered an invalid input.
   * @return A BotApiMethod that contains the invalid input message for the user.
   */
  public BotApiMethod<?> showInvalidInputMessage(Message message) {
    return createMessageResponse(message, INVALID_INPUT_MESSAGE);
  }

  /**
   * Sends the list of available commands to the user in response to a callback query.
   *
   * @param query The callback query received from the user.
   * @return A BotApiMethod that contains the available commands message for the user.
   */
  public BotApiMethod<?> showAvailableCommands(CallbackQuery query) {
    return createEditMessageResponse(
      query,
      AVAILABLE_COMMANDS_MESSAGE,
      dataKeyboardFactory.createMainOptionsMarkup()
    );
  }
}
