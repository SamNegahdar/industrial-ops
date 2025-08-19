package com.industrial.productionservice.infrastructure;
import com.industrial.productionservice.application.ProductionUseCases;
import com.industrial.productionservice.ports.out.WorkOrderRepository;
import com.industrial.productionservice.adapters.out.messaging.ProductionEventsProducer;
import org.springframework.context.annotation.*;
@Configuration
public class Beans {
  @Bean ProductionUseCases useCases(WorkOrderRepository repo, ProductionEventsProducer producer){ return new ProductionUseCases(repo, producer); }
}
