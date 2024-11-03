package com.example.notificationbot.keyboards;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

/**
 * A factory class for creating different types of keyboards for Telegram bot interactions.
 */
@Slf4j
@Component
public class KeyboardFactory {

  /**
   * Creates an inline keyboard markup for a Telegram bot message.
   *
   * @param texts          A list of button texts to be displayed on the keyboard.
   * @param configurations A list of configuration integers that determine button behavior.
   * @param data           A list of callback data associated with each button.
   * @return               An {@link InlineKeyboardMarkup} containing the constructed inline keyboard.
   * @throws IllegalArgumentException if the input lists are not of the same size or contain invalid values.
   */
  public InlineKeyboardMarkup createInlineKeyboard(List<String> texts, List<Integer> configurations, List<String> data) {
    validateInput(texts, configurations, data);
    List<List<InlineKeyboardButton>> keyboard = buildKeyboard(texts, configurations, data);
    return createKeyboardMarkup(keyboard);
  }

  private void validateInput(List<String> texts, List<Integer> configurations, List<String> data) {
    if (texts.size() != data.size() || texts.size() != configurations.stream().mapToInt(Integer::intValue).sum()) {
      String message = String.format("Invalid arguments: texts=%s, data=%s, configurations=%s", texts, data, configurations);
      log.warn(message);
      throw new IllegalArgumentException(message);
    }
  }

  private List<List<InlineKeyboardButton>> buildKeyboard(List<String> texts, List<Integer> configurations, List<String> data) {
    List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
    int index = 0;

    for (Integer rowCount : configurations) {
      List<InlineKeyboardButton> row = createRow(texts, data, index, rowCount);
      keyboard.add(row);
      index += rowCount;
    }

    return keyboard;
  }

  private List<InlineKeyboardButton> createRow(List<String> texts, List<String> data, int startIndex, int count) {
    List<InlineKeyboardButton> row = new ArrayList<>();

    for (int i = 0; i < count; i++) {
      InlineKeyboardButton button = new InlineKeyboardButton();
      button.setText(texts.get(startIndex + i));
      button.setCallbackData(data.get(startIndex + i));
      row.add(button);
    }

    return row;
  }

  private InlineKeyboardMarkup createKeyboardMarkup(List<List<InlineKeyboardButton>> keyboard) {
    InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
    keyboardMarkup.setKeyboard(keyboard);
    return keyboardMarkup;
  }
}
