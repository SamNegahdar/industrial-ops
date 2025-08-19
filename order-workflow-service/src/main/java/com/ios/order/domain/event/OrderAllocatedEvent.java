package com.ios.order.domain.event;

import lombok.*;
import java.time.Instant;

@Getter @Builder @AllArgsConstructor
public class OrderAllocatedEvent {
  private final BaseEvent meta;
  private final String orderId;
  private final Instant allocatedAt;
}
