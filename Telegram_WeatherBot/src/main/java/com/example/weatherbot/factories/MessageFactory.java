package com.example.weatherbot.factories;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * Factory for creating Telegram bot message responses.
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageFactory {

  /**
   * Creates an EditMessageText response for a given callback query.
   *
   * @param query       the CallbackQuery to respond to
   * @param text        the text to set in the edited message
   * @param replyMarkup the InlineKeyboardMarkup to attach to the message
   * @return an EditMessageText object configured with the provided parameters
   */
  public EditMessageText createEditMessageResponse(CallbackQuery query, String text, InlineKeyboardMarkup replyMarkup) {
    return EditMessageText.builder()
      .text(text)
      .chatId(query.getMessage().getChatId())
      .messageId(query.getMessage().getMessageId())
      .replyMarkup(replyMarkup)
      .build();
  }

  /**
   * Creates a SendMessage response for a given message.
   *
   * @param message     the Message to respond to
   * @param text        the text to send in the response
   * @param replyMarkup the InlineKeyboardMarkup to attach to the message
   * @return a SendMessage object configured with the provided parameters
   */
  public SendMessage createMessageResponse(Message message, String text, InlineKeyboardMarkup replyMarkup) {
    return SendMessage.builder()
      .chatId(message.getChatId())
      .text(text)
      .replyMarkup(replyMarkup)
      .build();
  }

  /**
   * Creates a SendMessage response using a chat ID.
   *
   * @param chatId the ID of the chat to send the message to
   * @param text   the text to send in the response
   * @return a SendMessage object configured with the provided parameters
   */
  public SendMessage createMessageResponse(Long chatId, String text) {
    return SendMessage.builder()
      .chatId(chatId)
      .text(text)
      .build();
  }

  /**
   * Creates an AnswerCallbackQuery response for a given callback query.
   *
   * @param query the CallbackQuery to respond to
   * @param text  the text to send as a response to the callback query
   * @return a BotApiMethod object configured as an answer to the callback query
   */
  public BotApiMethod<?> createAnswerCallback(CallbackQuery query, String text) {
    return AnswerCallbackQuery.builder()
      .callbackQueryId(query.getId())
      .text(text)
      .build();
  }
}
