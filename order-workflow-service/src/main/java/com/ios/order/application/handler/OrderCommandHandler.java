package com.ios.order.application.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ios.order.common.IdGenerator;
import com.ios.order.domain.command.*;
import com.ios.order.domain.event.*;
import com.ios.order.domain.model.*;
import com.ios.order.domain.repository.OrderRepository;
import com.ios.order.domain.service.OrderDomainService;
import com.ios.order.infrastructure.messaging.kafka.OutboxRepository;
import com.ios.order.infrastructure.messaging.kafka.OutboxEventEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderCommandHandler {
    private final OrderRepository orderRepository;
    private final OrderDomainService domainService = new OrderDomainService();
    private final OutboxRepository outboxRepository;
    private final ObjectMapper objectMapper;


    @Transactional
    public String handle(CreateOrderCommand cmd) {
        String orderId = IdGenerator.newId("O");
        var order = Order.draft(orderId, cmd.getCustomerId(), cmd.getLines(), cmd.getCurrency(), cmd.getTotal(), cmd.getSlaDueAt());
        orderRepository.save(order);

        var meta = BaseEvent.builder()
                .eventId(IdGenerator.newId("EV"))
                .type("order.created.v1")
                .tenantId(cmd.getTenantId())
                .occurredAt(Instant.now())
                .source("order-workflow-service")
                .build();

        var evt = OrderCreatedEvent.builder()
                .meta(meta)
                .orderId(order.getId())
                .customerId(order.getCustomerId())
                .lines(order.getLines())
                .currency(order.getCurrency())
                .total(order.getTotal())
                .slaDueAt(order.getSlaDueAt())
                .build();

        persistOutbox(evt);
        return orderId;
    }


    @Transactional
    public void handle(ConfirmOrderCommand cmd) {
        var order = orderRepository.findById(cmd.getOrderId()).orElseThrow();
        domainService.changeStatus(order, OrderStatus.CONFIRMED);
        orderRepository.save(order);

        var meta = BaseEvent.builder()
                .eventId(IdGenerator.newId("EV"))
                .type("order.confirmed.v1")
                .tenantId(cmd.getTenantId())
                .occurredAt(Instant.now())
                .source("order-workflow-service")
                .build();

        var evt = OrderConfirmedEvent.builder()
                .meta(meta)
                .orderId(order.getId())
                .confirmedAt(Instant.now())
                .build();

        persistOutbox(evt);
    }


    @Transactional
    public void handle(ChangeOrderStatusCommand cmd) {
        var order = orderRepository.findById(cmd.orderId()).orElseThrow();
        domainService.changeStatus(order, cmd.targetStatus());
        orderRepository.save(order);

        String type = switch (cmd.targetStatus()) {
            case ALLOCATED -> "order.allocated.v1";
            case SHIPPED -> "order.shipped.v1";
            case CANCELLED -> "order.cancelled.v1";
            default -> "order.updated.v1";
        };

        var meta = BaseEvent.builder()
                .eventId(IdGenerator.newId("EV"))
                .type(type)
                .tenantId(cmd.tenantId())
                .occurredAt(Instant.now())
                .source("order-workflow-service")
                .build();

        Object evt = switch (cmd.targetStatus()) {
            case ALLOCATED ->
                    OrderAllocatedEvent.builder().meta(meta).orderId(order.getId()).allocatedAt(Instant.now()).build();
            case SHIPPED ->
                    OrderShippedEvent.builder().meta(meta).orderId(order.getId()).shippedAt(Instant.now()).build();
            default -> meta;
        };

        persistOutbox(evt);
    }


    private void persistOutbox(Object evt) {
        try {
            String payload = objectMapper.writeValueAsString(evt);
            outboxRepository.save(OutboxEventEntity.pending("order.events.v1", payload , UUID.randomUUID().toString()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
