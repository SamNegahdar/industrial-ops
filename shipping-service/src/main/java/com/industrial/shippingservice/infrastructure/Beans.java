package com.industrial.shippingservice.infrastructure;
import com.industrial.shippingservice.application.ShippingUseCases;
import com.industrial.shippingservice.ports.out.ShipmentRepository;
import org.springframework.context.annotation.*;
@Configuration
public class Beans {
  @Bean ShippingUseCases useCases(ShipmentRepository repo){ return new ShippingUseCases(repo); }
}
