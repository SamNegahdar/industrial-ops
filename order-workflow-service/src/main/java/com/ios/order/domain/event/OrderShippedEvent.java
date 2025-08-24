package com.ios.order.domain.event;

import lombok.*;

import java.time.Instant;

@Builder
@AllArgsConstructor
public record OrderShippedEvent(BaseEvent meta, String orderId, Instant shippedAt) {
}
