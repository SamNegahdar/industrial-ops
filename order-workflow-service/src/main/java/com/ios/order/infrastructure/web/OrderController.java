package com.ios.order.infrastructure.web;

import com.ios.order.application.dto.*;
import com.ios.order.application.handler.OrderCommandHandler;
import com.ios.order.domain.command.*;
import com.ios.order.domain.model.OrderLine;
import com.ios.order.domain.model.OrderStatus;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
  private final OrderCommandHandler handler;
  public OrderController(OrderCommandHandler handler) { this.handler = handler; }

  @PostMapping
  public ResponseEntity<String> create(@RequestHeader(name = "X-Tenant-Id", required = false, defaultValue = "t1") String tenant,
                                       @Valid @RequestBody CreateOrderRequest req) {
    var lines = req.lines().stream().map(l -> new OrderLine(l.sku(), l.qty())).toList();
    var cmd = CreateOrderCommand.builder()
        .commandId("cmd-" + System.nanoTime())
        .tenantId(tenant)
        .customerId(req.customerId())
        .lines(lines)
        .currency(req.currency())
        .total(req.total())
        .slaDueAt(req.slaDueAt())
        .build();
    String id = handler.handle(cmd);
    return ResponseEntity.ok(id);
  }

  @PatchMapping("/{id}/status")
  public ResponseEntity<Void> changeStatus(@RequestHeader(name = "X-Tenant-Id", required = false, defaultValue = "t1") String tenant,
                                           @PathVariable String id,
                                           @Valid @RequestBody ChangeStatusRequest req) {
    if (req.targetStatus() == OrderStatus.CONFIRMED) {
      handler.handle(ConfirmOrderCommand.builder().commandId("cmd-"+System.nanoTime()).tenantId(tenant).orderId(id).build());
    } else {
      handler.handle(ChangeOrderStatusCommand.builder().commandId("cmd-"+System.nanoTime()).tenantId(tenant).orderId(id).targetStatus(req.targetStatus()).build());
    }
    return ResponseEntity.noContent().build();
  }
}
