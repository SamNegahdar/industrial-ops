package com.industrial.shippingservice.adapters.out.persistence;
import com.industrial.shippingservice.domain.*;
import com.industrial.shippingservice.ports.out.ShipmentRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.UUID;
@Component
public class ShipmentRepositoryAdapter implements ShipmentRepository {
  private final SpringDataShipmentRepo repo;
  public ShipmentRepositoryAdapter(SpringDataShipmentRepo r){ this.repo=r; }
  public ShipmentId save(Shipment s){
    var e = new JpaShipment();
    e.setId(s.id().value()); e.setOrderId(s.orderId());
    e.setCarrier(s.carrier()); e.setTracking(s.tracking());
    e.setStatus(s.status().name());
    e.setCreatedAt(new java.util.Date(java.time.Instant.now().toEpochMilli()));
    repo.save(e); return s.id();
  }
  public Optional<Shipment> findById(ShipmentId id){
    return repo.findById(id.value()).map(e -> new Shipment(new ShipmentId(e.getId()), e.getOrderId()));
  }
}
