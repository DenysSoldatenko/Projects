package com.example.militarytrackerbot.handlers;

import com.example.militarytrackerbot.managers.MainManager;
import com.example.militarytrackerbot.services.DateRangeParserService;
import com.example.militarytrackerbot.services.MessageProviderService;
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
  MessageProviderService messageProviderService;
  DateRangeParserService dateInputParserService;

  /**
   * Handles incoming bot commands by routing them to the appropriate manager method.
   *
   * @param object The bot API object that contains the command information,
   *               which is expected to be a {@link Message}.
   * @return A {@link BotApiMethod} that represents the bot's response to the command.
   * @throws UnsupportedOperationException If the command is not supported or not yet implemented.
   */
  public BotApiMethod<?> handle(BotApiObject object) {
    var message = (Message) object;
    String text = message.getText().trim();
    String command = text.contains(" ") ? text.substring(0, text.indexOf(" ")).toLowerCase() : text.toLowerCase();
    String date = text.contains(" ") ? text.substring(text.indexOf(" ")).toLowerCase() : text.toLowerCase();

    if ("/set".equals(command) && date.length() < 10) {
      return mainManager.showInvalidInputMessage(message);
    }

    return switch (command) {
      case "/start" -> mainManager.showMainMenu(message);
      case "/help" -> mainManager.showHelpMessage(message);
      case "/day" -> messageProviderService.provideLatestDayData(message);
      case "/week" -> messageProviderService.provideWeeklyData(message);
      case "/month" -> messageProviderService.provideMonthlyData(message);
      case "/set" -> dateInputParserService.processDateInput(message, date);
      default -> mainManager.showInvalidInputMessage(message);
    };
  }
}