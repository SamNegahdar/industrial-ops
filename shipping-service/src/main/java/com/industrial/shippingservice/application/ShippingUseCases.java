package com.industrial.shippingservice.application;
import com.industrial.shippingservice.application.commands.*;
import com.industrial.shippingservice.application.queries.GetShipmentQuery;
import com.industrial.shippingservice.domain.*;
import com.industrial.shippingservice.ports.out.ShipmentRepository;
import java.util.UUID;
public class ShippingUseCases {
  private final ShipmentRepository repo;
  public ShippingUseCases(ShipmentRepository r){ this.repo=r; }
  public ShipmentId handle(CreateShipmentCommand c){
    var s = new Shipment(ShipmentId.newId(), c.orderId());
    return repo.save(s);
  }
  public void handle(AssignCarrierCommand c){
    var s = repo.findById(new ShipmentId(UUID.fromString(c.shipmentId()))).orElseThrow();
    s.assign(c.carrier(), c.tracking()); repo.save(s);
  }
  public void handle(MarkInTransitCommand c){
    var s = repo.findById(new ShipmentId(UUID.fromString(c.shipmentId()))).orElseThrow();
    s.inTransit(); repo.save(s);
  }
  public void handle(MarkDeliveredCommand c){
    var s = repo.findById(new ShipmentId(UUID.fromString(c.shipmentId()))).orElseThrow();
    s.delivered(); repo.save(s);
  }
  public Shipment handle(GetShipmentQuery q){
    return repo.findById(new ShipmentId(UUID.fromString(q.id()))).orElseThrow();
  }
}
