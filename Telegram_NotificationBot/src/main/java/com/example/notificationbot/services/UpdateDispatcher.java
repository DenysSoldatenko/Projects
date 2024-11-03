package com.example.notificationbot.services;

import com.example.notificationbot.configurations.TelegramBot;
import com.example.notificationbot.entities.Action;
import com.example.notificationbot.entities.User;
import com.example.notificationbot.handlers.CommandHandler;
import com.example.notificationbot.handlers.MessageHandler;
import com.example.notificationbot.handlers.QueryHandler;
import com.example.notificationbot.repositories.UserRepository;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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
  UserRepository userRepository;

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
      if (update.hasCallbackQuery()) {
        return handleCallbackQuery(update.getCallbackQuery(), telegramBot);
      } else if (update.hasMessage()) {
        return handleMessage(update.getMessage(), telegramBot);
      }
    } catch (TelegramApiException e) {
      log.error("Error processing update: {}", update, e);
      throw new RuntimeException(e);
    }

    log.warn("Unsupported update type: {}", update);
    return createUnsupportedResponse(update);
  }

  private BotApiMethod<?> handleCallbackQuery(CallbackQuery callbackQuery, TelegramBot telegramBot) throws TelegramApiException {
    checkUser(callbackQuery.getMessage().getChatId());
    return queryHandler.handle(callbackQuery, telegramBot);
  }

  private BotApiMethod<?> handleMessage(Message message, TelegramBot telegramBot) throws TelegramApiException {
    checkUser(message.getChatId());
    if (message.hasText()) {
      return message.getText().startsWith("/")
        ? commandHandler.handle(message, telegramBot)
        : messageHandler.handle(message, telegramBot);
    }
    return createUnsupportedResponse(message);
  }

  private void checkUser(Long chatId) {
    if (!userRepository.existsByChatId(chatId)) {
      userRepository.save(
          User.builder()
            .action(Action.FREE)
            .registeredAt(LocalDateTime.now())
            .chatId(chatId)
            .firstName("User")
            .build()
      );
    }
  }

  private BotApiMethod<?> createUnsupportedResponse(Object object) {
    String chatId = object instanceof Message ? String.valueOf(((Message) object).getChatId()) : String.valueOf(((CallbackQuery) object).getMessage().getChatId());
    return new SendMessage(chatId, "I don't recognize that command. Please try again.");
  }
}
