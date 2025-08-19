package com.ios.order.infrastructure.messaging.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {
  @Bean
  public NewTopic orderEventsTopic() {
    return new NewTopic(KafkaTopics.ORDER_EVENTS_V1, 3, (short) 1);
  }
}
