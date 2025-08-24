package com.ios.order.domain.model;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OrderLine {
  private String sku; // کد یکتای کالا
  private int qty;    // تعداد آیتم


}
