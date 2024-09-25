package com.example.demo.configurations;

import org.apache.kafka.clients.admin.NewTopic;
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

  @Bean
  public NewTopic demoTopic() {
    return TopicBuilder.name("Demo").build();
  }

  @Bean
  public NewTopic demoJsonTopic() {
    return TopicBuilder.name("Demo_json").build();
  }
}
