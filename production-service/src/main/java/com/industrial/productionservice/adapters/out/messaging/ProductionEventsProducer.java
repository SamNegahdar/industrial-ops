package com.industrial.productionservice.adapters.out.messaging;
import com.industrial.productionservice.domain.events.WorkOrderCompleted;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductionEventsProducer {
  private final KafkaTemplate<String,Object> kafka;
  public ProductionEventsProducer(KafkaTemplate<String,Object> k){ this.kafka=k; }
  public void publishCompleted(String workOrderId, String orderId){
    kafka.send("production-events", orderId, new WorkOrderCompleted(workOrderId, orderId));
  }
}
