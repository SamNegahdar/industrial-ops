package com.industrial.productionservice.adapters.in.messaging;
import com.industrial.productionservice.application.ProductionUseCases;
import com.industrial.productionservice.application.commands.CreateWorkOrderCommand;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class OrderEventsListener {
  private final ProductionUseCases useCases;
  public OrderEventsListener(ProductionUseCases u){ this.useCases=u; }

  @KafkaListener(topics = "order-events", groupId = "production-service")
  public void onOrderConfirmed(ConsumerRecord<String, Object> record){
    var orderId = record.key();
    useCases.handle(new CreateWorkOrderCommand(orderId, List.of()));
  }
}
