package com.example.notificationbot.services;

import com.example.notificationbot.configurations.TelegramBot;
import com.example.notificationbot.entities.Action;
import com.example.notificationbot.entities.User;
import com.example.notificationbot.exceptions.MessageNotTextException;
import com.example.notificationbot.exceptions.UpdateProcessingException;
import com.example.notificationbot.handlers.CommandHandler;
import com.example.notificationbot.handlers.MessageHandler;
import com.example.notificationbot.handlers.QueryHandler;
import com.example.notificationbot.repositories.UserRepository;
import java.time.LocalDateTime;
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
      throw new UpdateProcessingException("Failed to process update", e);
    }
    throw new UpdateProcessingException("Update does not contain a valid message or callback query", null);
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
    throw new MessageNotTextException("Message does not contain text");
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
}
