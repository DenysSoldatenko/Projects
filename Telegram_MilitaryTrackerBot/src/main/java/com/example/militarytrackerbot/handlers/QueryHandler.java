package com.example.militarytrackerbot.handlers;

import com.example.militarytrackerbot.managers.MilitaryTrackerManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

/**
 * Handler for processing callback queries received by the Telegram bot.
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QueryHandler {

  MilitaryTrackerManager militaryTrackerManager;

  public BotApiMethod<?> handle(BotApiObject object) {
    var query = (CallbackQuery) object;
    return militaryTrackerManager.processQuery(query, query.getData());
  }
}
