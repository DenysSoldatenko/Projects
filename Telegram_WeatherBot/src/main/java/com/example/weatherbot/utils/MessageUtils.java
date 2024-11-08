package com.example.weatherbot.utils;

import lombok.experimental.UtilityClass;

/**
 * Utility class that contains predefined message templates for the Telegram Weather Bot.
 * This class helps centralize the bot's messaging logic
 * and ensures consistency across the application.
 */
@UtilityClass
public class MessageUtils {

  public static final String WELCOME_MESSAGE_TEMPLATE = """
        👋 Welcome to the Weather Forecast Bot!
    
        ✅ This bot was created for demonstration purposes
        ✅ It uses the WeatherBit API
        ✅ Technologies used: Java & Spring Boot
    
        To get the weather forecast, please enter the city name followed by the forecast mode:
    
        🌍 Example 1: "London 24h" — Get the weather forecast for the next 24 hours in London
        🌍 Example 2: "Paris 5d" — Get the weather forecast for the next 5 days in Paris
    
        📍 Type a city and the forecast mode (e.g., '24h' for hourly or '5d' for daily).
    
        Enjoy and stay updated with the weather! 😊
        """;

  public static final String INVALID_INPUT_MESSAGE = """
        ⚠️ Oops! It seems like your input is invalid.
    
        Please provide the city name followed by the forecast mode (e.g., '24h' for hourly or '5d' for daily).
    
        Example: "London 24h" — Get the weather forecast for the next 24 hours in London.
        """;


  public static final String WEATHER_FETCH_ERROR_MESSAGE = """
        🚨 Something went wrong while fetching the weather data.
    
        Please try again later or check your city and forecast mode input.
        """;

  public static final String NO_WEATHER_DATA_MESSAGE = """
        🚨 No weather data available!

        Please check the city name or the forecast mode and try again.
        If the issue persists, please try later.
        """;
}
