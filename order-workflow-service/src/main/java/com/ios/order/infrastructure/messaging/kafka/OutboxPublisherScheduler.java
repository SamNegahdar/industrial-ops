package com.ios.order.infrastructure.messaging.kafka;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;

@Component
public class OutboxPublisherScheduler {
  private final OutboxRepository repo;
  private final OrderEventPublisher publisher;

  public OutboxPublisherScheduler(OutboxRepository repo, OrderEventPublisher publisher) {
    this.repo = repo; this.publisher = publisher;
  }

  @Scheduled(fixedDelay = 2000)
  @Transactional
  public void publishPending() {
    var batch = repo.findTop50ByStatusOrderByCreatedAtAsc("PENDING");
    for (var e : batch) {
      try {
        publisher.publish(e.getTopic(), null, e.getPayload());
        e.setStatus("SENT");
      } catch (Exception ex) {
        e.setStatus("FAILED");
      }
      e.setLastTriedAt(Instant.now());
    }
  }
}
