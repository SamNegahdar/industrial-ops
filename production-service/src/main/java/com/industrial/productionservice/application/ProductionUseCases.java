package com.industrial.productionservice.application;
import com.industrial.productionservice.application.commands.*;
import com.industrial.productionservice.application.queries.GetWorkOrderQuery;
import com.industrial.productionservice.domain.*;
import com.industrial.productionservice.ports.out.WorkOrderRepository;
import java.util.UUID;

public class ProductionUseCases {
  private final WorkOrderRepository repo;
  private final com.industrial.productionservice.adapters.out.messaging.ProductionEventsProducer producer;
  public ProductionUseCases(WorkOrderRepository r, com.industrial.productionservice.adapters.out.messaging.ProductionEventsProducer p){ this.repo=r; this.producer=p; }

  public WorkOrderId handle(CreateWorkOrderCommand cmd){
    var wo = new WorkOrder(WorkOrderId.newId(), cmd.orderId());
    for (var i : cmd.items()) wo.addLine(new WorkOrderLine(i.sku(), i.quantity()));
    return repo.save(wo);
  }
  public void handle(StartWorkOrderCommand cmd){
    var id = UUID.fromString(cmd.workOrderId());
    var wo = repo.findById(new WorkOrderId(id)).orElseThrow();
    wo.start();
    repo.save(wo);
    producer.publishCompleted(cmd.workOrderId(), wo.orderId());
  }
  public void handle(CompleteWorkOrderCommand cmd){
    var id = UUID.fromString(cmd.workOrderId());
    var wo = repo.findById(new WorkOrderId(id)).orElseThrow();
    wo.complete();
    repo.save(wo);
    producer.publishCompleted(cmd.workOrderId(), wo.orderId());
  }
  public WorkOrder handle(GetWorkOrderQuery q){
    var id = UUID.fromString(q.id());
    return repo.findById(new WorkOrderId(id)).orElseThrow();
  }
}
