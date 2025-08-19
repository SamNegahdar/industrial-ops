package com.ios.order.domain.command;

import com.ios.order.domain.model.OrderStatus;
import lombok.*;

@Getter @Builder @AllArgsConstructor
public class ChangeOrderStatusCommand {
  private final String commandId;
  private final String tenantId;
  private final String orderId;
  private final OrderStatus targetStatus;
}
