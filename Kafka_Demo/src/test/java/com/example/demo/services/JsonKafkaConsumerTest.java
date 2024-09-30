package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.dtos.ProductMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

/**
 * Tests for the {@link JsonKafkaConsumer} class.
 */
@EnableKafka
@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = { "test-topic" })
public class JsonKafkaConsumerTest {

  @Autowired
  private KafkaTemplate<String, ProductMessage> kafkaTemplate;

  @DynamicPropertySource
  static void properties(DynamicPropertyRegistry registry) {
    registry.add("spring.kafka.topic-json.name", () -> "test-topic");
  }

  @Test
  public void testConsume() throws Exception {
    ProductMessage productMessage = new ProductMessage("1", "Test Product", 10.99, true);
    kafkaTemplate.send("test-topic", productMessage).get();

    assertEquals("1", productMessage.productId());
    assertEquals("Test Product", productMessage.productName());
    assertEquals(10.99, productMessage.price());
  }
}
