package com.example.militarytrackerbot.factories;

import static com.example.militarytrackerbot.data.CallbackData.BACK;
import static com.example.militarytrackerbot.data.CallbackData.DAY;
import static com.example.militarytrackerbot.data.CallbackData.MAIN;
import static com.example.militarytrackerbot.data.CallbackData.MONTH;
import static com.example.militarytrackerbot.data.CallbackData.OPTIONS;
import static com.example.militarytrackerbot.data.CallbackData.PERIOD;
import static com.example.militarytrackerbot.data.CallbackData.WEEK;

import com.example.militarytrackerbot.keyboards.KeyboardFactory;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * The {@link MilitaryDataKeyboardFactory} class is responsible
 * for creating different keyboard layouts that can be used in the bot's interface,
 * specifically the inline keyboard options for various user interactions.
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MilitaryDataKeyboardFactory {

  KeyboardFactory keyboardFactory;

  /**
   * Creates a keyboard markup with main menu options for the user.
   * The options include time period selections (Day, Week, Month),
   * a specific date option, and a back button to return to the main menu.
   *
   * @return An {@link InlineKeyboardMarkup} object containing the main menu options for the user.
   */
  public InlineKeyboardMarkup createMainOptionsMarkup() {
    List<String> text = List.of(
        "📅 Latest Day", "📅 Latest Week",
        "📅 Latest Month", "🔍 Set Specific Date",
        "🔙 Main"
    );

    List<String> callbackData = List.of(
        DAY.name(), WEEK.name(),
        MONTH.name(), PERIOD.name(),
        MAIN.name()
    );

    return keyboardFactory.createInlineKeyboard(text, List.of(2, 2, 1), callbackData);
  }

  /**
   * Creates a keyboard markup with a "Back" button.
   * This button is used to navigate back to the previous menu or screen.
   *
   * @param id The identifier used to construct the callback data for the back button.
   * @return An {@link InlineKeyboardMarkup} object containing the "Back" button
   *     with the specified callback data.
   */
  public InlineKeyboardMarkup createBackButtonMarkup(String id) {
    return keyboardFactory.createInlineKeyboard(
      List.of("🔙 Back"),
      List.of(1),
      List.of(BACK + id)
    );
  }

  /**
   * Creates a keyboard markup with a single "View Bot Options" button.
   * This button is used to show the available bot commands and options.
   *
   * @return An {@link InlineKeyboardMarkup} object containing the "View Bot Options" button.
   */
  public InlineKeyboardMarkup createOptionsMarkup() {
    return keyboardFactory.createInlineKeyboard(
      List.of("📢 View Bot Options"),
      List.of(1),
      List.of(OPTIONS.name())
    );
  }
}
