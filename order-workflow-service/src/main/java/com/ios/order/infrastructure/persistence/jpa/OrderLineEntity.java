package com.ios.order.infrastructure.persistence.jpa;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "order_lines")
@Getter @Setter @NoArgsConstructor
public class OrderLineEntity {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String sku;
  private int qty;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private OrderEntity order;
}
