package com.industrial.warehouseservice.adapters.out.persistence;
import com.industrial.warehouseservice.domain.*;
import com.industrial.warehouseservice.ports.out.TaskRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.UUID;
@Component
public class TaskRepositoryAdapter implements TaskRepository {
  private final SpringDataTaskRepo repo;
  public TaskRepositoryAdapter(SpringDataTaskRepo r){ this.repo=r; }
  public TaskId save(WHTask t){
    var e = new JpaTask();
    e.setId(t.id().value()); e.setType(t.type().name()); e.setRef(t.ref());
    e.setSku(t.sku()); e.setQuantity(t.quantity()); e.setStatus(t.status().name());
    e.setCreatedAt(new java.util.Date(java.time.Instant.now().toEpochMilli()));
    repo.save(e); return t.id();
  }
  public Optional<WHTask> findById(TaskId id){
    return repo.findById(id.value()).map(e -> new WHTask(new TaskId(e.getId()), TaskType.valueOf(e.getType()), e.getRef(), e.getSku(), e.getQuantity()));
  }
}
