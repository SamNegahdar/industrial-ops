package com.industrial.warehouseservice.ports.out;
import com.industrial.warehouseservice.domain.*;
import java.util.Optional;
public interface TaskRepository {
  TaskId save(WHTask t);
  Optional<WHTask> findById(TaskId id);
}
