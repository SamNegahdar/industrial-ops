package com.industrial.shippingservice.adapters.out.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface SpringDataShipmentRepo extends JpaRepository<JpaShipment, UUID>{}
