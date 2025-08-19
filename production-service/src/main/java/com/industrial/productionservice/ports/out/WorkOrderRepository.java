package com.industrial.productionservice.ports.out;
import com.industrial.productionservice.domain.*;
import java.util.Optional;
public interface WorkOrderRepository {
  WorkOrderId save(WorkOrder wo);
  Optional<WorkOrder> findById(WorkOrderId id);
}
