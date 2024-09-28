package com.example.demo.configurations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.apache.kafka.clients.admin.NewTopic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * Tests for the {@link KafkaTopicConfiguration} class.
 */
@SpringBootTest
@TestPropertySource(properties = {
  "spring.kafka.topic.name=test-topic",
  "spring.kafka.topic-json.name=test-json-topic"
})
public class KafkaTopicConfigurationTest {

  @Autowired
  private KafkaTopicConfiguration kafkaTopicConfiguration;

  @Value("${spring.kafka.topic.name}")
  private String expectedTopic;

  @Value("${spring.kafka.topic-json.name}")
  private String expectedJsonTopic;

  @Test
  public void testDemoTopicCreation() {
    NewTopic demoTopic = kafkaTopicConfiguration.demoTopic();
    assertNotNull(demoTopic);
    assertEquals(expectedTopic, demoTopic.name());
  }

  @Test
  public void testDemoJsonTopicCreation() {
    NewTopic demoJsonTopic = kafkaTopicConfiguration.demoJsonTopic();
    assertNotNull(demoJsonTopic);
    assertEquals(expectedJsonTopic, demoJsonTopic.name());
  }
}
