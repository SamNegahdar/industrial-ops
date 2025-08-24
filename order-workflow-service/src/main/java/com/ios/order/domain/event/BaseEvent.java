package com.ios.order.domain.event;

import lombok.*;

import java.time.Instant;

/**
 * @param type   e.g., order.created.v1
 * @param source e.g., order-workflow-service
 */

@Builder
@AllArgsConstructor
public record BaseEvent(String eventId, String type, String tenantId, Instant occurredAt, String source) {
}
