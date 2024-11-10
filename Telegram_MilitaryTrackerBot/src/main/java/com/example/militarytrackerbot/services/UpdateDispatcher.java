package com.example.militarytrackerbot.services;

import com.example.militarytrackerbot.exceptions.MessageNotTextException;
import com.example.militarytrackerbot.exceptions.UpdateProcessingException;
import com.example.militarytrackerbot.handlers.CommandHandler;
import com.example.militarytrackerbot.handlers.MessageHandler;
import com.example.militarytrackerbot.handlers.QueryHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Service for dispatching incoming updates from the Telegram bot.
 */
@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateDispatcher {

  QueryHandler queryHandler;
  MessageHandler messageHandler;
  CommandHandler commandHandler;

  /**
   * Processes incoming updates from the Telegram bot.
   *
   * <p>Handles callback queries and messages. Returns an unsupported response for
   * unrecognized update types.
   *
   * @param update the incoming {@link Update}.
   * @return a {@link BotApiMethod} representing the response to the user.
   */
  public BotApiMethod<?> processUpdate(Update update) {
    try {
      if (update.hasCallbackQuery()) {
        return handleCallbackQuery(update.getCallbackQuery());
      } else if (update.hasMessage()) {
        return handleMessage(update.getMessage());
      }
    } catch (TelegramApiException e) {
      log.error("Error processing update: {}", update, e);
      throw new UpdateProcessingException("Failed to process update", e);
    }
    throw new UpdateProcessingException("Update does not contain a valid message or callback query", null);
  }

  private BotApiMethod<?> handleCallbackQuery(CallbackQuery callbackQuery) throws TelegramApiException {
    return queryHandler.handle(callbackQuery);
  }

  private BotApiMethod<?> handleMessage(Message message) throws TelegramApiException {
    if (message.hasText()) {
      return message.getText().startsWith("/")
        ? commandHandler.handle(message)
        : messageHandler.handle(message);
    }
    throw new MessageNotTextException("Message does not contain text");
  }
}
