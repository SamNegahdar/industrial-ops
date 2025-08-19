package com.industrial.warehouseservice.domain;
import java.time.Instant;
import java.util.Objects;
public class WHTask {
  private final TaskId id;
  private final TaskType type;
  private final String ref; // orderId or ASN
  private final String sku;
  private final int quantity;
  private TaskStatus status;
  private final Instant createdAt = Instant.now();
  public WHTask(TaskId id, TaskType type, String ref, String sku, int quantity){
    this.id = Objects.requireNonNull(id);
    this.type = Objects.requireNonNull(type);
    this.ref = Objects.requireNonNull(ref);
    this.sku = Objects.requireNonNull(sku);
    if(quantity<=0) throw new IllegalArgumentException("qty>0");
    this.quantity = quantity;
    this.status = TaskStatus.OPEN;
  }
  public void start(){ if(status!=TaskStatus.OPEN) throw new IllegalStateException(); status=TaskStatus.IN_PROGRESS; }
  public void complete(){ if(status!=TaskStatus.IN_PROGRESS) throw new IllegalStateException(); status=TaskStatus.DONE; }
  public TaskId id(){return id;} public TaskType type(){return type;} public String ref(){return ref;}
  public String sku(){return sku;} public int quantity(){return quantity;} public TaskStatus status(){return status;}
  public Instant createdAt(){return createdAt;}
}
