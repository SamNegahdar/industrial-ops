package com.industrial.inventoryservice.infrastructure;
import com.industrial.inventoryservice.application.InventoryUseCases;
import com.industrial.inventoryservice.ports.out.StockRepository;
import org.springframework.context.annotation.*;
@Configuration
public class Beans {
  @Bean InventoryUseCases useCases(StockRepository repo){ return new InventoryUseCases(repo); }
}
