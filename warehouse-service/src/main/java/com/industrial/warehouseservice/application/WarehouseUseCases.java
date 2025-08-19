package com.industrial.warehouseservice.application;
import com.industrial.warehouseservice.application.commands.*;
import com.industrial.warehouseservice.application.queries.GetTaskQuery;
import com.industrial.warehouseservice.domain.*;
import com.industrial.warehouseservice.ports.out.TaskRepository;
import java.util.UUID;
public class WarehouseUseCases {
  private final TaskRepository repo;
  public WarehouseUseCases(TaskRepository r){ this.repo=r; }
  public TaskId handle(CreateReceivingCommand c){
    var t = new WHTask(TaskId.newId(), TaskType.RECEIVE, c.asnId(), c.sku(), c.quantity());
    return repo.save(t);
  }
  public TaskId handle(CreatePickCommand c){
    var t = new WHTask(TaskId.newId(), TaskType.PICK, c.orderId(), c.sku(), c.quantity());
    return repo.save(t);
  }
  public void handle(StartTaskCommand c){
    var t = repo.findById(new TaskId(UUID.fromString(c.taskId()))).orElseThrow();
    t.start(); repo.save(t);
  }
  public void handle(CompleteTaskCommand c){
    var t = repo.findById(new TaskId(UUID.fromString(c.taskId()))).orElseThrow();
    t.complete(); repo.save(t);
  }
  public WHTask handle(GetTaskQuery q){
    return repo.findById(new TaskId(UUID.fromString(q.id()))).orElseThrow();
  }
}
