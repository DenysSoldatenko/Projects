package com.example.weatherbot.factories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 * Factory for creating Telegram bot message responses.
 */
@Component
@RequiredArgsConstructor
public class MessageFactory {

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
}
