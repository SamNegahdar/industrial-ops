package com.ios.order.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

@Getter
@Builder
@AllArgsConstructor
public class Order {
    private final String id;
    private final String customerId;
    private final List<OrderLine> lines;
    private OrderStatus status;
    private final String currency;
    private final BigDecimal total;
    private final Instant slaDueAt;

    public static Order draft(String id, String customerId, List<OrderLine> lines, String currency, BigDecimal total, Instant slaDueAt) {
        return Order.builder()
                .id(id)
                .customerId(customerId)
                .lines(List.copyOf(lines))
                .status(OrderStatus.DRAFT)
                .currency(currency)
                .total(total)
                .slaDueAt(slaDueAt)
                .build();
    }

    public void confirm() {
        if (status != OrderStatus.DRAFT) throw new IllegalStateException("Only DRAFT can be CONFIRMED");
        status = OrderStatus.CONFIRMED;
    }

    public void allocate() {
        if (status != OrderStatus.CONFIRMED) throw new IllegalStateException("Only CONFIRMED can be ALLOCATED");
        status = OrderStatus.ALLOCATED;
    }

    public void ship() {
        if (status != OrderStatus.ALLOCATED) throw new IllegalStateException("Only ALLOCATED can be SHIPPED");
        status = OrderStatus.SHIPPED;
    }

    public void cancel() {
        if (status == OrderStatus.SHIPPED) throw new IllegalStateException("Cannot cancel SHIPPED");
        status = OrderStatus.CANCELLED;
    }
}
