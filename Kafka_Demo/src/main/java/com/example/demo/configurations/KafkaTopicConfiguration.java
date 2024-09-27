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

  @Bean
  public NewTopic demoTopic() {
    return TopicBuilder.name(topic).build();
  }

  @Bean
  public NewTopic demoJsonTopic() {
    return TopicBuilder.name(jsonTopic).build();
  }
}
