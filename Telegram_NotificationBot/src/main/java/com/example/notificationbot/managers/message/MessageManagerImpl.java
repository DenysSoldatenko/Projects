package com.example.notificationbot.managers.message;

import com.example.notificationbot.configurations.TelegramBot;
import com.example.notificationbot.repositories.UserRepository;
import com.example.notificationbot.utlis.ReplyMarkupEditor;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Manages messages related to notifications in the Telegram bot.
 * This class handles user interactions through messages and updates
 * the chat interface accordingly.
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageManagerImpl implements MessageManager {

  UserRepository userRepository;
  ReplyMarkupEditor replyMarkupEditor;

  @Override
  public BotApiMethod<?> showMainMenu(Message message, TelegramBot bot) {
    return SendMessage.builder()
      .chatId(message.getChatId())
      .text("Configure Notification")
      .replyMarkup(
        replyMarkupEditor.editNotificationReplyMarkup(
          String.valueOf(userRepository.findByChatId(message.getChatId()).getCurrentNotification())
        )
      )
      .build();
  }
}
