package com.example.pastebox.utils;

import lombok.experimental.UtilityClass;
import java.util.UUID;

@UtilityClass
public class LinkGenerator {

  public String generate(){
    return UUID.randomUUID().toString().substring(0, 8);
  }
}
