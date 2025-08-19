package com.ios.order.infrastructure.messaging.kafka;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OutboxRepository extends JpaRepository<OutboxEventEntity, Long> {
  List<OutboxEventEntity> findTop50ByStatusOrderByCreatedAtAsc(String status);
}
