package com.ios.order.domain.event;

import com.ios.order.domain.model.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Builder
@AllArgsConstructor
public record OrderCreatedEvent(BaseEvent meta,
                                String orderId,
                                String customerId,
                                List<OrderLine> lines,
                                String currency,
                                BigDecimal total,
                                Instant slaDueAt) {
}

