package com.example.demo.configurations;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Configuration class for Kafka topics.
 *
 * <p>Defines the Kafka topics used in the application.
 */
@Configuration
public class KafkaTopicConfiguration {

  @Value("${spring.kafka.topic.name}")
  private String topic;

  @Value("${spring.kafka.topic-json.name}")
  private String jsonTopic;

  /**
   * Bean definition for the regular Kafka topic.
   * Creates a Kafka topic using the topic name defined in the application properties.
   *
   * @return A NewTopic object representing the regular Kafka topic.
   */
  @Bean
  public NewTopic demoTopic() {
    return TopicBuilder.name(topic).build();
  }

  /**
   * Bean definition for the JSON Kafka topic.
   * Creates a Kafka topic for JSON messages using
   * the topic name defined in the application properties.
   *
   * @return A NewTopic object representing the JSON Kafka topic.
   */
  @Bean
  public NewTopic demoJsonTopic() {
    return TopicBuilder.name(jsonTopic).build();
  }
}
