package com.ios.order.domain.event;

import lombok.*;
import java.time.Instant;

@Getter @Builder @AllArgsConstructor
public class BaseEvent {
  private final String eventId;
  private final String type;      // e.g., order.created.v1
  private final String tenantId;
  private final Instant occurredAt;
  private final String source;    // e.g., order-workflow-service
}
