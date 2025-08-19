package com.industrial.shippingservice.ports.out;
import com.industrial.shippingservice.domain.*;
import java.util.Optional;
public interface ShipmentRepository {
  ShipmentId save(Shipment s);
  Optional<Shipment> findById(ShipmentId id);
}
