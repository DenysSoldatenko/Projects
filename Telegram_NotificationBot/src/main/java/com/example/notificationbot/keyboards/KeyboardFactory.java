package com.example.notificationbot.keyboards;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

/**
 * A factory class for creating different types of keyboards for Telegram bot interactions.
 */
@Slf4j
@Component
public class KeyboardFactory {

  /**
   * Creates an inline keyboard with the specified button text, configuration, and callback data.
   *
   * @param text the list of button texts to be displayed on the inline keyboard
   * @param configuration the list of integers representing the number of buttons in each row
   * @param data the list of callback data for each button, which is sent back to the bot when the button is pressed
   * @return an InlineKeyboardMarkup object representing the created inline keyboard,
   *         or null if the provided arguments are invalid
   */
  public InlineKeyboardMarkup createInlineKeyboard(List<String> text, List<Integer> configuration, List<String> data) {
    if (!isValidInlineKeyboardArgs(text, configuration, data)) {
      log.warn("Invalid arguments for inline keyboard: [{}]", text);
      return null;
    }
    return buildInlineKeyboard(text, configuration, data);
  }

  private boolean isValidInlineKeyboardArgs(List<String> text, List<Integer> configuration, List<String> data) {
    return text.size() == data.size() && text.size() == configuration.stream().mapToInt(Integer::intValue).sum();
  }

  private InlineKeyboardMarkup buildInlineKeyboard(List<String> text, List<Integer> configuration, List<String> data) {
    List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
    int index = 0;

    for (Integer rowNumber : configuration) {
      List<InlineKeyboardButton> row = new ArrayList<>();
      for (int i = 0; i < rowNumber; i++) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(text.get(index));
        button.setCallbackData(data.get(index));
        row.add(button);
        index++;
      }
      keyboard.add(row);
    }

    InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
    keyboardMarkup.setKeyboard(keyboard);
    return keyboardMarkup;
  }

  /**
   * Creates a reply keyboard with the specified button text and configuration.
   *
   * @param text the list of button texts to be displayed on the reply keyboard
   * @param configuration the list of integers representing the number of buttons in each row
   * @return a ReplyKeyboard object representing the created reply keyboard,
   *         or null if the provided arguments are invalid
   */
  public ReplyKeyboard createReplyKeyboard(List<String> text, List<Integer> configuration) {
    if (!isValidReplyKeyboardArgs(text, configuration)) {
      log.warn("Invalid arguments for reply keyboard: [{}]", text);
      return null;
    }
    return buildReplyKeyboard(text, configuration);
  }

  private boolean isValidReplyKeyboardArgs(List<String> text, List<Integer> configuration) {
    return text.size() == configuration.stream().mapToInt(Integer::intValue).sum();
  }

  private ReplyKeyboard buildReplyKeyboard(List<String> text, List<Integer> configuration) {
    List<KeyboardRow> keyboard = new ArrayList<>();
    int index = 0;

    for (Integer rowNumber : configuration) {
      KeyboardRow row = new KeyboardRow();
      for (int i = 0; i < rowNumber; i++) {
        KeyboardButton button = new KeyboardButton();
        button.setText(text.get(index));
        row.add(button);
        index++;
      }
      keyboard.add(row);
    }

    ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
    keyboardMarkup.setKeyboard(keyboard);
    keyboardMarkup.setResizeKeyboard(true);
    keyboardMarkup.setOneTimeKeyboard(true);
    return keyboardMarkup;
  }
}
