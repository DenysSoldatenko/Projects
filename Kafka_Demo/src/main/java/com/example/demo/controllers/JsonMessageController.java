package com.example.demo.controllers;

import com.example.demo.dtos.ProductMessage;
import com.example.demo.services.JsonKafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

/**
 * Controller for handling Kafka JSON message publishing requests.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/kafka")
public class JsonMessageController {

  private final JsonKafkaProducer producer;

  @PostMapping("/publish")
  public ResponseEntity<String> publish(@RequestBody ProductMessage productMessage) {
    producer.sendMessage(productMessage);
    return ok("JSON message successfully sent to Kafka");
  }
}
