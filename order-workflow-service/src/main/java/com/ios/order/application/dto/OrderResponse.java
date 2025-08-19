package com.ios.order.application.dto;

import com.ios.order.domain.model.OrderStatus;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderResponse(
  String id,
  String customerId,
  List<Line> lines,
  String currency,
  BigDecimal total,
  Instant slaDueAt,
  OrderStatus status
) {
  public record Line(String sku, int qty) {}
}
