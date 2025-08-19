package com.industrial.shippingservice.adapters.in.messaging;
import com.industrial.shippingservice.application.ShippingUseCases;
import com.industrial.shippingservice.application.commands.CreateShipmentCommand;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductionEventsListener {
  private final ShippingUseCases uc;
  public ProductionEventsListener(ShippingUseCases u){ this.uc=u; }

  @KafkaListener(topics = "production-events", groupId = "shipping-service")
  public void onCompleted(ConsumerRecord<String,Object> record){
    var orderId = record.key();
    uc.handle(new CreateShipmentCommand(orderId));
  }
}
