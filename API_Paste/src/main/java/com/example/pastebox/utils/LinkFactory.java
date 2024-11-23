package com.example.pastebox.utils;

import static java.util.UUID.randomUUID;

import lombok.experimental.UtilityClass;

/**
 * Utility class for generating unique links.
 */
@UtilityClass
public class LinkFactory {

  /**
   * Generates a unique 8-character short link.
   * This method creates a UUID, converts it to a string, and returns the first 8 characters
   * to be used as a unique short link.
   *
   * @return A unique 8-character string suitable for use as a short link or identifier.
   */
  public String generateLink() {
    return randomUUID().toString().substring(0, 8);
  }
}
