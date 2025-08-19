package com.industrial.billingservice.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Domain {
  public static class Invoice {
    private UUID id;
    @NotBlank private String orderId;
    private List<Line> lines = new ArrayList<>();
    private BigDecimal subtotal = BigDecimal.ZERO;
    private BigDecimal tax = BigDecimal.ZERO;
    private BigDecimal total = BigDecimal.ZERO;
    private Status status = Status.DRAFT;
    private Instant createdAt = Instant.now();
    public enum Status { DRAFT, ISSUED, PAID, CANCELLED }

    public UUID getId(){ return id; }
    public String getOrderId(){ return orderId; }
    public List<Line> getLines(){ return lines; }
    public BigDecimal getSubtotal(){ return subtotal; }
    public BigDecimal getTax(){ return tax; }
    public BigDecimal getTotal(){ return total; }
    public Status getStatus(){ return status; }
    public Instant getCreatedAt(){ return createdAt; }
    public void setId(UUID id){ this.id = id; }
    public void setOrderId(String orderId){ this.orderId = orderId; }
    public void setLines(List<Line> lines){ this.lines = lines; }
    public void setSubtotal(BigDecimal subtotal){ this.subtotal = subtotal; }
    public void setTax(BigDecimal tax){ this.tax = tax; }
    public void setTotal(BigDecimal total){ this.total = total; }
    public void setStatus(Status status){ this.status = status; }
    public void setCreatedAt(Instant createdAt){ this.createdAt = createdAt; }
  }
  public static class Line {
    @NotBlank private String title;
    @Min(1) private int qty;
    @Min(0) private BigDecimal unitPrice;

    public String getTitle(){ return title; }
    public int getQty(){ return qty; }
    public BigDecimal getUnitPrice(){ return unitPrice; }
    public void setTitle(String title){ this.title = title; }
    public void setQty(int qty){ this.qty = qty; }
    public void setUnitPrice(BigDecimal unitPrice){ this.unitPrice = unitPrice; }
  }
}
