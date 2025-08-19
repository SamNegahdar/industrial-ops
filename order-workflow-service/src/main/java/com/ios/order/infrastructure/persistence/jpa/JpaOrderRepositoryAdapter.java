package com.ios.order.infrastructure.persistence.jpa;

import com.ios.order.domain.model.*;
import com.ios.order.domain.repository.OrderRepository;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JpaOrderRepositoryAdapter implements OrderRepository {
  private final OrderJpaRepository repo;
  public JpaOrderRepositoryAdapter(OrderJpaRepository repo) { this.repo = repo; }

  @Override
  public Order save(Order order) {
    var e = toEntity(order);
    repo.save(e);
    return toDomain(e);
  }
  @Override
  public Optional<Order> findById(String id) {
    return repo.findById(id).map(this::toDomain);
  }
  @Override
  public boolean existsById(String id) { return repo.existsById(id); }

  private OrderEntity toEntity(Order o) {
    var e = new OrderEntity();
    e.setId(o.getId());
    e.setCustomerId(o.getCustomerId());
    e.setCurrency(o.getCurrency());
    e.setTotal(o.getTotal());
    e.setSlaDueAt(o.getSlaDueAt());
    e.setStatus(o.getStatus().name());
    var lines = o.getLines().stream().map(l -> {
      var le = new OrderLineEntity();
      le.setSku(l.getSku());
      le.setQty(l.getQty());
      le.setOrder(e);
      return le;
    }).collect(Collectors.toList());
    e.getLines().clear();
    e.getLines().addAll(lines);
    return e;
  }

  private Order toDomain(OrderEntity e) {
    var lines = e.getLines().stream()
        .map(le -> new OrderLine(le.getSku(), le.getQty()))
        .collect(Collectors.toList());
    return Order.builder()
        .id(e.getId())
        .customerId(e.getCustomerId())
        .lines(lines)
        .status(OrderStatus.valueOf(e.getStatus()))
        .currency(e.getCurrency())
        .total(e.getTotal())
        .slaDueAt(e.getSlaDueAt())
        .build();
  }
}
