package com.ios.order.domain.command;

import com.ios.order.domain.model.OrderStatus;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
public record ChangeOrderStatusCommand(String commandId, String tenantId, String orderId, OrderStatus targetStatus) {
}
