package com.example.militarytrackerbot.factories;

import static com.example.militarytrackerbot.data.CallbackData.BACK;
import static com.example.militarytrackerbot.data.CallbackData.DAY;
import static com.example.militarytrackerbot.data.CallbackData.EMPTY$INPUT;
import static com.example.militarytrackerbot.data.CallbackData.INPUT$;
import static com.example.militarytrackerbot.data.CallbackData.MAIN;
import static com.example.militarytrackerbot.data.CallbackData.MONTH;
import static com.example.militarytrackerbot.data.CallbackData.NEXT$;
import static com.example.militarytrackerbot.data.CallbackData.OPTIONS;
import static com.example.militarytrackerbot.data.CallbackData.PERIOD;
import static com.example.militarytrackerbot.data.CallbackData.PREV$;
import static com.example.militarytrackerbot.data.CallbackData.SUBMIT$INPUT;
import static com.example.militarytrackerbot.data.CallbackData.WEEK;

import com.example.militarytrackerbot.keyboards.KeyboardFactory;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * The {@link DataKeyboardFactory} class is responsible
 * for creating different keyboard layouts that can be used in the bot's interface,
 * specifically the inline keyboard options for various user interactions.
 */
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DataKeyboardFactory {

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
   * @return An {@link InlineKeyboardMarkup} object containing the "Back" button
   *     with the specified callback data.
   */
  public InlineKeyboardMarkup createBackButtonMarkup() {
    return keyboardFactory.createInlineKeyboard(
      List.of("🔙 Back"),
      List.of(1),
      List.of(BACK.name())
    );
  }

  /**
   * Creates pagination buttons for navigating through pages,
   * with options to move to the "Previous" page,
   * move to the "Next" page, or "Go Back" to the previous menu.
   *
   * @return An {@link InlineKeyboardMarkup} object
   *     with the "Previous", "Next", and "Go Back" buttons.
   */
  public InlineKeyboardMarkup createPaginationButtonsMarkup(String url) {
    return keyboardFactory.createInlineKeyboard(
      List.of("⬅️ Previous", "➡️ Next", "🔙 Back"),
      List.of(2, 1),
      List.of(PREV$.name() + url, NEXT$.name() + url, BACK.name())
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

  /**
   * Creates a keyboard markup for entering a period, with dynamic input options.
   * This keyboard includes number buttons, input field, and options for submitting,
   * clearing, or removing the input. It also provides a back button.
   *
   * @param current The current value in the input field. If the field is empty, a placeholder ("Input field") is used.
   * @return An {@link InlineKeyboardMarkup} object containing the period entering keyboard.
   */
  public InlineKeyboardMarkup createPeriodEnteringKeyboardMarkup(String current) {
    return keyboardFactory.createInlineKeyboard(
      List.of(
        !current.isEmpty() ? current : "Input field",
        "1", "2", "3", "4",
        "5", "6", "7", "8",
        "9", "0", "-", "–",
        "✔ Submit", "❌ Remove", "🗑️ Clear",
        "🔙 Back"
      ),
      List.of(1, 4, 4, 4, 3, 1),
      List.of(
        EMPTY$INPUT.name(),
        INPUT$.name() + current + "1", INPUT$.name() + current + "2", INPUT$.name() + current + "3", INPUT$.name() + current + "4",
        INPUT$.name() + current + "5", INPUT$.name() + current + "6", INPUT$.name() + current + "7", INPUT$.name() + current + "8",
        INPUT$.name() + current + "9", INPUT$.name() + current + "0", INPUT$.name() + current + "-", INPUT$.name() + current + " – ",
        SUBMIT$INPUT.name() + current, current.length() <= 1 ? EMPTY$INPUT.name() : INPUT$.name() + current.substring(0, current.length() - 1), EMPTY$INPUT.name(),
        BACK.name()
      )
    );
  }
}
