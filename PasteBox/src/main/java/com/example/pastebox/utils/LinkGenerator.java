package com.example.pastebox.utils;

import java.util.UUID;
import lombok.experimental.UtilityClass;

/**
 * Utility class for generating unique links.
 */
@UtilityClass
public class LinkGenerator {

  public String generate() {
    return UUID.randomUUID().toString().substring(0, 8);
  }
}
