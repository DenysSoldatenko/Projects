package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.dtos.MessageRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

/**
 * Tests for the {@link KafkaConsumer} class.
 */
@EnableKafka
@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = { "test-topic" })
public class KafkaConsumerTest {

  @Autowired
  private KafkaTemplate<String, MessageRequest> kafkaTemplate;

  @DynamicPropertySource
  static void properties(DynamicPropertyRegistry registry) {
    registry.add("spring.kafka.topic.name", () -> "test-topic");
  }

  @Test
  public void testConsume() throws Exception {
    MessageRequest messageRequest = new MessageRequest("Test 123");
    kafkaTemplate.send("test-topic", messageRequest).get();
    assertEquals("Test 123", messageRequest.message());
  }
}
