package com.ios.order.domain.event;

import lombok.*;

import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
public record OrderConfirmedEvent(BaseEvent meta, String orderId, Instant confirmedAt) {
}
