package com.example.militarytrackerbot.data;

/**
 * Enum representing callback data for Telegram bot actions.
 *
 * <p>Each constant corresponds to a specific action or state within
 * the bot that deals with military data, updates, and status reports.</p>
 */
public enum CallbackData {
  MAIN,           // Main menu for the bot
  OPTIONS,        // View available options
  DAY,            // View data for the latest day
  WEEK,           // View data for the latest week
  MONTH,          // View data for the latest month
  PERIOD,         // Action to set a specific time period
  BACK,           // Navigate back to the main menu
  PREV$,          // Navigate to the previous page (for pagination)
  NEXT$           // Navigate to the next page (for pagination)
}