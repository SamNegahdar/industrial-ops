package com.industrial.warehouseservice.infrastructure;
import com.industrial.warehouseservice.application.WarehouseUseCases;
import com.industrial.warehouseservice.ports.out.TaskRepository;
import org.springframework.context.annotation.*;
@Configuration
public class Beans {
  @Bean WarehouseUseCases useCases(TaskRepository repo){ return new WarehouseUseCases(repo); }
}
