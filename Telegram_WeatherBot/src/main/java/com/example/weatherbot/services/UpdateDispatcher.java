package com.example.weatherbot.services;

import com.example.weatherbot.configurations.TelegramBot;
import com.example.weatherbot.exceptions.MessageNotTextException;
import com.example.weatherbot.exceptions.UpdateProcessingException;
import com.example.weatherbot.handlers.CommandHandler;
import com.example.weatherbot.handlers.MessageHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
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

  MessageHandler messageHandler;
  CommandHandler commandHandler;

  /**
   * Processes incoming updates from the Telegram bot.
   *
   * <p>Handles callback queries and messages. Returns an unsupported response for
   * unrecognized update types.
   *
   * @param update the incoming {@link Update}.
   * @param telegramBot the {@link TelegramBot} instance for sending responses.
   * @return a {@link BotApiMethod} representing the response to the user.
   */
  public BotApiMethod<?> processUpdate(Update update, TelegramBot telegramBot) {
    try {
      return handleMessage(update.getMessage(), telegramBot);
    } catch (TelegramApiException e) {
      log.error("Error processing update: {}", update, e);
      throw new UpdateProcessingException("Failed to process update", e);
    }
  }

  private BotApiMethod<?> handleMessage(Message message, TelegramBot telegramBot) throws TelegramApiException {
    if (message.hasText()) {
      return message.getText().startsWith("/")
        ? commandHandler.handle(message, telegramBot)
        : messageHandler.handle(message, telegramBot);
    }
    throw new MessageNotTextException("Message does not contain text");
  }
}
