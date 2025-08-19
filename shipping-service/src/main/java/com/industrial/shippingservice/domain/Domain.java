package com.industrial.shippingservice.domain;

import jakarta.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.UUID;

public class Domain {
  public static class Shipment {
    private UUID id;
    @NotBlank private String orderId;
    @NotBlank private String address;
    private Status status = Status.CREATED;
    private String tracking;
    private Instant createdAt = Instant.now();
    public enum Status { CREATED, PICKED, IN_TRANSIT, DELIVERED, CANCELLED }

    public UUID getId(){ return id; }
    public String getOrderId(){ return orderId; }
    public String getAddress(){ return address; }
    public Status getStatus(){ return status; }
    public String getTracking(){ return tracking; }
    public Instant getCreatedAt(){ return createdAt; }
    public void setId(UUID id){ this.id = id; }
    public void setOrderId(String orderId){ this.orderId = orderId; }
    public void setAddress(String address){ this.address = address; }
    public void setStatus(Status status){ this.status = status; }
    public void setTracking(String tracking){ this.tracking = tracking; }
    public void setCreatedAt(Instant createdAt){ this.createdAt = createdAt; }
  }
}
