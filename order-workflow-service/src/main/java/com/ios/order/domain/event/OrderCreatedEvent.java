package com.ios.order.domain.event;

import com.ios.order.domain.model.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter @Builder @AllArgsConstructor
public class OrderCreatedEvent {
  private final BaseEvent meta;
  private final String orderId;
  private final String customerId;
  private final List<OrderLine> lines;
  private final String currency;
  private final BigDecimal total;
  private final Instant slaDueAt;
}
