package com.example.notificationbot.managers;

import com.example.notificationbot.configurations.TelegramBot;
import com.example.notificationbot.listeners.MessageListener;
import com.example.notificationbot.listeners.QueryListener;
import com.example.notificationbot.managers.helpers.MessageNotificationHelper;
import com.example.notificationbot.managers.helpers.QueryNotificationHelper;
import com.example.notificationbot.managers.query.QueryManager;
import com.example.notificationbot.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationManager implements QueryListener, MessageListener {

  QueryManager queryManager;
  UserRepository userRepository;
  MessageNotificationHelper messageHelper;
  QueryNotificationHelper queryHelper;

  @Override
  public BotApiMethod<?> processMessage(Message message, TelegramBot bot) {
    var user = userRepository.findByChatId(message.getChatId());

    return switch (user.getAction()) {
      case SENDING_TIME -> messageHelper.editTime(message, user, bot);
      case SENDING_DESCRIPTION -> messageHelper.editDescription(message, user, bot);
      case SENDING_TITLE -> messageHelper.editTitle(message, user, bot);
      default -> throw new IllegalStateException("Unexpected action: " + user.getAction());
    };
  }

  @Override
  public BotApiMethod<?> processQuery(CallbackQuery query, String[] words, TelegramBot bot) throws TelegramApiException {
    return switch (words.length) {
      case 2 -> handleTwoWordsQuery(words, query, bot);
      case 3 -> handleThreeWordsQuery(words, query, bot);
      case 4 -> handleFourWordsQuery(words, query);
      default -> throw new IllegalStateException("Unexpected number of words: " + words.length);
    };
  }

  private BotApiMethod<?> handleTwoWordsQuery(String[] words, CallbackQuery query, TelegramBot bot) {
    return switch (words[1]) {
      case "main" -> queryManager.showMainMenu(query, bot);
      case "new" -> queryHelper.newNotification(query);
      default -> throw new IllegalStateException("Unexpected value: " + words[1]);
    };
  }

  private BotApiMethod<?> handleThreeWordsQuery(String[] words, CallbackQuery query, TelegramBot bot) throws TelegramApiException {
    return switch (words[1]) {
      case "back" -> queryHelper.editPage(query, words[2]);
      case "done" -> queryHelper.sendNotification(query, words[2], bot);
      default -> throw new IllegalStateException("Unexpected value: " + words[1]);
    };
  }

  private BotApiMethod<?> handleFourWordsQuery(String[] words, CallbackQuery query) {
    if ("edit".equals(words[1])) {
      return switch (words[2]) {
        case "title" -> queryHelper.askTitle(query, words[3]);
        case "d" -> queryHelper.askDescription(query, words[3]);
        case "time" -> queryHelper.askSeconds(query, words[3]);
        default -> throw new IllegalStateException("Unexpected value: " + words[2]);
      };
    }
    throw new IllegalStateException("Unexpected value: " + words[1]);
  }
}
