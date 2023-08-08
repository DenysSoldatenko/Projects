package com.example.springsecuritysystem;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vi/greetings")
public class GreetingController {

  @GetMapping("/say-hello")
  public ResponseEntity<String> sayHello() {
    return ResponseEntity.ok("Hello from API!");
  }

  @GetMapping("/say-good-bye")
  public ResponseEntity<String> sayGoodBye() {
    return ResponseEntity.ok("Goodbye and see you later!");
  }
}
