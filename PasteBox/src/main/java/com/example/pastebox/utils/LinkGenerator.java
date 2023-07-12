package com.example.pastebox.utils;

import java.util.UUID;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LinkGenerator {

  public String generate() {
    return UUID.randomUUID().toString().substring(0, 8);
  }
}
