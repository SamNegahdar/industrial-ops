package com.ios.order.infrastructure.persistence.jpa;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

@Entity @Table(name = "orders")
@Getter @Setter @NoArgsConstructor
public class OrderEntity {
  @Id
  private String id;
  private String customerId;
  private String currency;
  private BigDecimal total;
  private Instant slaDueAt;
  private String status;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<OrderLineEntity> lines = new ArrayList<>();
}
