package com.ios.order.domain.command;

import lombok.*;

@Getter @Builder @AllArgsConstructor
public class ConfirmOrderCommand {
  private final String commandId;
  private final String tenantId;
  private final String orderId;
}
