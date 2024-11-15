package com.example.militarytrackerbot.handlers;

import com.example.militarytrackerbot.managers.MainManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Handler for processing commands received by the Telegram bot.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommandHandler {

  MainManager mainManager;

  /**
   * Handles incoming bot commands by routing them to the appropriate manager method.
   *
   * @param object The bot API object that contains the command information,
   *               which is expected to be a {@link Message}.
   * @return A {@link BotApiMethod} that represents the bot's response to the command.
   * @throws UnsupportedOperationException If the command is not supported or not yet implemented.
   */
  public BotApiMethod<?> handle(BotApiObject object) {
    var command = (Message) object;
    if (command.getText().equals("/start")) {
      return mainManager.showMainMenu(command);
    }
    throw new UnsupportedOperationException("Method not implemented for command: " + command.getText());
  }
}