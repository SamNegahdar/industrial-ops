package com.industrial.productionservice.domain;
import java.time.Instant;
import java.util.*;
public class WorkOrder {
  private final WorkOrderId id;
  private final String orderId;
  private ProductionStatus status;
  private final List<WorkOrderLine> lines = new ArrayList<>();
  private final Instant createdAt = Instant.now();

  public WorkOrder(WorkOrderId id, String orderId){
    this.id = Objects.requireNonNull(id);
    this.orderId = Objects.requireNonNull(orderId);
    this.status = ProductionStatus.PLANNED;
  }
  public void addLine(WorkOrderLine l){
    if(status!=ProductionStatus.PLANNED) throw new IllegalStateException("add only in PLANNED");
    lines.add(Objects.requireNonNull(l));
  }
  public void start(){ if(status!=ProductionStatus.PLANNED) throw new IllegalStateException(); status=ProductionStatus.IN_PROGRESS; }
  public void complete(){ if(status!=ProductionStatus.IN_PROGRESS) throw new IllegalStateException(); status=ProductionStatus.COMPLETED; }
  public WorkOrderId id(){return id;}
  public String orderId(){return orderId;}
  public ProductionStatus status(){return status;}
  public List<WorkOrderLine> lines(){return List.copyOf(lines);}
  public Instant createdAt(){return createdAt;}
}
