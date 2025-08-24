package com.ios.order.domain.repository;

import com.ios.order.domain.model.Order;

import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);

    Optional<Order> findById(String id);

    boolean existsById(String id);
}
