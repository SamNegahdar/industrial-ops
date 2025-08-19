package com.industrial.inventoryservice.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.Map;
import java.util.UUID;

public class Domain {
  public static class Sku {
    @NotBlank private String code;
    @Min(0) private long available;
    @Min(0) private long reserved;

    public String getCode() { return code; }
    public long getAvailable() { return available; }
    public long getReserved() { return reserved; }
    public void setCode(String code) { this.code = code; }
    public void setAvailable(long available) { this.available = available; }
    public void setReserved(long reserved) { this.reserved = reserved; }
  }

  public static class Reservation {
    private UUID id;
    @NotBlank private String sku;
    @Min(1) private long qty;
    public UUID getId() { return id; }
    public String getSku() { return sku; }
    public long getQty() { return qty; }
    public void setId(UUID id) { this.id = id; }
    public void setSku(String sku) { this.sku = sku; }
    public void setQty(long qty) { this.qty = qty; }
  }
}
