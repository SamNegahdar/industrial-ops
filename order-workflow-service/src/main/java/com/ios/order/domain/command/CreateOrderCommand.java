package com.ios.order.domain.command;

import com.ios.order.domain.model.OrderLine;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Getter @Builder @AllArgsConstructor
public class CreateOrderCommand {
  private final String commandId;
  private final String tenantId;
  private final String customerId;
  private final List<OrderLine> lines;
  private final String currency;
  private final BigDecimal total;
  private final Instant slaDueAt;
}
