package com.ios.order.infrastructure.messaging.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisher {
  private final KafkaTemplate<String, String> kafkaTemplate;
  public OrderEventPublisher(KafkaTemplate<String, String> kafkaTemplate) { this.kafkaTemplate = kafkaTemplate; }
  public void publish(String topic, String key, String payload) {
    kafkaTemplate.send(topic, key, payload);
  }
}
