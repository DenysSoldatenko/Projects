package com.example.notificationbot.data;

/**
 * Enum representing callback data for Telegram bot actions.
 *
 * <p>Each constant corresponds to a specific action or state
 * within the notification management feature of the bot.
 */
public enum CallbackData {
  main,
  notification_main,
  notification_new,
  notification_edit_title_,
  notification_edit_desc_,
  notification_edit_time_,
  notification_done_,
  notification_back_
}
