package com.example.militarytrackerbot.utils;

import lombok.experimental.UtilityClass;

/**
 * Utility class that contains predefined message templates for the Telegram Military Tracker Bot.
 * This class centralizes the bot's messaging logic and ensures consistency across the application.
 */
@UtilityClass
public class MessageUtils {

  public static final String WELCOME_MESSAGE_TEMPLATE = """
      ⚔️ Welcome to the Russian Losses Tracker Bot! ⚔️
  
      🚨 Here, we track the massive losses Russia has sustained in this war!
      From military personnel to tanks, helicopters, and artillery, we keep an eye on it all.
  
      💥 Check out the staggering losses of the Russian invaders:
  
      - 📅 View the latest daily, weekly, or monthly losses.
      - 🔎 Get details for a specific date or custom time period.
  
      ✅ We provide real-time updates on:
      - Russian personnel and vehicles lost
      - Aircraft, tanks, artillery systems, and more!
  
      🌍 Example Commands:
      - /day — Get the most recent daily losses.
      - /week — See the weekly military losses.
      - /month — View the losses for the past month.
      - /set [date] — View losses for a specific date
        (e.g., /set 2024-11-07).
      - /set [date one] – [date two] — View losses for a specific date range
        (e.g., /set 2024-11-07 – 2024-11-09).
  
      💪 Watch as the invaders' losses continue to grow…
  
      🇺🇦 Glory to Ukraine!
      """;

  public static final String COMMANDS_ONLY_MESSAGE = """
      ⚠️ Please note, this bot only responds to commands (e.g., /day, /week, /set) or button clicks.
      
      🚫 Text messages are not supported for processing. Please use the provided buttons or type a valid command to proceed.
  
      ❗ For more details, use /help.
      """;

  public static final String INVALID_INPUT_MESSAGE = """
      ⚠️ Oops! Your input is invalid or the query could not be processed. Please use one of the following commands:
    
      - /day — Get the most recent daily losses.
      - /week — See the weekly military losses.
      - /month — View the losses for the past month.
      - /set [date] — View losses for a specific date
        (e.g., /set 2024-11-07).
      - /set [date one] – [date two] — View losses for a specific date range
        (e.g., /set 2024-11-07 – 2024-11-09).
    
      ❗ Please try again with a valid command or refer to /help for more guidance.
      """;

  public static final String MILITARY_DATA_FETCH_ERROR_MESSAGE = """
      🚨 Something went wrong while fetching the loss data!
  
      The Russian invaders' losses continue to rise, but we couldn't retrieve the data right now.
      ⚠️ Please try again later or check your input.

      💥 The war rages on...
      """;

  public static final String UNEXPECTED_ERROR_MESSAGE = """
      🚨 An unexpected error occurred while processing your request!
  
      ⚠️ Please try again later. If the problem persists, contact support.
  
      💥 Thank you for your patience, and stay tuned for updates!
      """;

  public static final String NO_MILITARY_DATA_MESSAGE = """
      🚨 No military loss data found for the selected period! 😞
  
      📅 Double-check your command or date format and try again.
      🔄 If you still see this message, check back later for updates.

      🇺🇦 Ukraine's victory will be recorded, just stay tuned!
      """;

  public static final String HELP_MESSAGE = """
      🛠️ How to use the Russian Losses Tracker Bot:
  
      The bot tracks losses of Russian forces in the war.
      You can request updates based on different time periods or a specific date:
  
      - /day — Get the most recent daily losses.
      - /week — See the weekly military losses.
      - /month — View the losses for the past month.
      - /set [date] — View losses for a specific date
        (e.g., /set 2024-11-07).
      - /set [date one] – [date two] — View losses for a specific date range
        (e.g., /set 2024-11-07 – 2024-11-09).
  
      You can also use these commands:
      - /start — Start the bot and see the welcome message 👋.
      - /help — See this help message again for guidance 🛠️.
  
      🇺🇦 Glory to Ukraine!
      """;

  public static final String STATS_DISPLAY_MESSAGE_LATEST_DAY = """
      📊 Russian Military Losses for Day %d (Date: %s) 📊
  
      🔴 Personnel Units: %s
      🚗 Tanks: %s
      🎯 Artillery Systems: %s
      🛩️ Planes: %s
      🚁 Helicopters: %s
      🚚 Fuel Vehicles: %s
      🚢 Warships / Cutters: %s
      🛸 Cruise Missiles: %s
      🔧 Special Military Equipment: %s
      🌊 Submarines: %s
  
      ➕ Increase Since Last Report:
      🔼 Personnel Units: %s
      🔼 Tanks: %s
      🔼 Artillery Systems: %s
      🔼 Planes: %s
      🔼 Helicopters: %s
      🔼 Fuel Vehicles: %s
      🔼 Warships / Cutters: %s
      🔼 Cruise Missiles: %s
      🔼 Special Military Equipment: %s
      🔼 Submarines: %s
      
      ⚠️ The statistics are based on the most recent data collected.
      🚨 Keep an eye on future updates to see how the losses continue to rise!
      """;

  public static final String STATS_DISPLAY_MESSAGE_PERIOD = """
      📊 Russian Military Losses for the Period
      🕒 From: %s to: %s

      %s

      ⚠️ The statistics are based on the most recent data collected for the selected period.
      🚨 Stay tuned for continuous updates!
      """;

  public static final String STATS_DAY_STATS_TEMPLATE = """
      📅 Date: %s

      🔴 Personnel Units: %s
      🚗 Tanks: %s
      🎯 Artillery Systems: %s
      🛩️ Planes: %s
      🚁 Helicopters: %s
      🚚 Fuel Vehicles: %s
      🚢 Warships / Cutters: %s
      🛸 Cruise Missiles: %s
      🔧 Special Military Equipment: %s
      🌊 Submarines: %s
  
      ➕ Increase Since Last Report:
      🔼 Personnel Units: %s
      🔼 Tanks: %s
      🔼 Artillery Systems: %s
      🔼 Planes: %s
      🔼 Helicopters: %s
      🔼 Fuel Vehicles: %s
      🔼 Warships / Cutters: %s
      🔼 Cruise Missiles: %s
      🔼 Special Military Equipment: %s
      🔼 Submarines: %s

      ----------------------------------------
      """;

  public static final String INVALID_SINGLE_DATE_FORMAT_MESSAGE = """
      ⚠️ Invalid date format!
    
      Please enter a valid date in the format: yyyy-MM-dd (e.g., 2024-11-07).
      Double-check your input and try again.
      """;

  public static final String INVALID_DATE_RANGE_FORMAT_MESSAGE = """
      ⚠️ Invalid date range format!
    
      Please enter a valid date range in the format ⬇️
      
      yyyy-MM-dd – yyyy-MM-dd (e.g., 2024-11-07 – 2024-11-09).
      
      Make sure both dates are in the correct format and try again.
      """;

  public static final String INVALID_START_DATE_MESSAGE = """
      ⚠️ Invalid start date in the range!
    
      Please check your start date format and ensure it's a valid date in the format: yyyy-MM-dd.
      """;

  public static final String INVALID_END_DATE_MESSAGE = """
      ⚠️ Invalid end date in the range!
    
      Please check your end date format and ensure it's a valid date in the format: yyyy-MM-dd.
      """;

  public static final String END_DATE_BEFORE_START_DATE_MESSAGE = """
      ⚠️ End date cannot be before the start date!
    
      Please ensure the end date is after the start date and try again.
      """;

  public static final String ENTER_PERIOD_OR_DATE_MESSAGE = """ 
      📅 Please enter a period or a single date ⬇️
      """;

  public static final String AVAILABLE_COMMANDS_MESSAGE = """
      📜 Available Commands ⬇️
      """;
}
