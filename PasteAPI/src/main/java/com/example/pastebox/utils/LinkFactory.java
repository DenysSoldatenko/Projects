package com.example.pastebox.utils;

import static java.util.UUID.randomUUID;

import lombok.experimental.UtilityClass;

/**
 * Utility class for generating unique links.
 */
@UtilityClass
public class LinkFactory {

  public String generateLink() {
    return randomUUID().toString().substring(0, 8);
  }
}
