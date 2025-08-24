package com.ios.order.domain.event;

import lombok.*;
import java.time.Instant;

@Builder
@AllArgsConstructor
public record OrderAllocatedEvent(BaseEvent meta, String orderId, Instant allocatedAt) {
}
