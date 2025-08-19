package com.industrial.shippingservice.domain;
import java.time.Instant;
import java.util.Objects;
public class Shipment {
  private final ShipmentId id;
  private final String orderId;
  private String carrier;
  private String tracking;
  private ShipmentStatus status;
  private final Instant createdAt = Instant.now();
  public Shipment(ShipmentId id, String orderId){ this.id=Objects.requireNonNull(id); this.orderId=Objects.requireNonNull(orderId); this.status=ShipmentStatus.READY; }
  public void assign(String carrier, String tracking){ if(status!=ShipmentStatus.READY) throw new IllegalStateException(); this.carrier=carrier; this.tracking=tracking; }
  public void inTransit(){ if(status!=ShipmentStatus.READY) throw new IllegalStateException(); status=ShipmentStatus.IN_TRANSIT; }
  public void delivered(){ if(status!=ShipmentStatus.IN_TRANSIT) throw new IllegalStateException(); status=ShipmentStatus.DELIVERED; }
  public ShipmentId id(){return id;} public String orderId(){return orderId;} public ShipmentStatus status(){return status;}
  public String carrier(){return carrier;} public String tracking(){return tracking;} public Instant createdAt(){return createdAt;}
}
