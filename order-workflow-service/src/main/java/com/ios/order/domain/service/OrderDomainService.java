package com.ios.order.domain.service;

import com.ios.order.domain.model.Order;
import com.ios.order.domain.model.OrderStatus;

public class OrderDomainService {
  public void changeStatus(Order order, OrderStatus target) {
    switch (target) {
      case CONFIRMED -> order.confirm();
      case ALLOCATED -> order.allocate();
      case SHIPPED   -> order.ship();
      case CANCELLED -> order.cancel();
      default -> throw new IllegalArgumentException("Unsupported transition");
    }
  }
}
