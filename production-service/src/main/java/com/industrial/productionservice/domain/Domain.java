package com.industrial.productionservice.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;
import java.time.Instant;
import java.util.UUID;

public class Domain {
  public static class WorkOrder {
    private UUID id;
    @NotBlank private String productSku;
    @Min(1) private long quantity;
    private Status status = Status.NEW;
    private Instant createdAt = Instant.now();
    public enum Status { NEW, IN_PROGRESS, DONE, CANCELLED }

    public UUID getId() { return id; }
    public String getProductSku() { return productSku; }
    public long getQuantity() { return quantity; }
    public Status getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }
    public void setId(UUID id) { this.id = id; }
    public void setProductSku(String productSku) { this.productSku = productSku; }
    public void setQuantity(long quantity) { this.quantity = quantity; }
    public void setStatus(Status status) { this.status = status; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
  }
}
