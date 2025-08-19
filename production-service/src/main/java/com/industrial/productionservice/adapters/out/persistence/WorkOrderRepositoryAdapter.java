package com.industrial.productionservice.adapters.out.persistence;
import com.industrial.productionservice.domain.*;
import com.industrial.productionservice.ports.out.WorkOrderRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;
import java.util.UUID;

@Component
public class WorkOrderRepositoryAdapter implements WorkOrderRepository {
  private final SpringDataWORepo repo;
  public WorkOrderRepositoryAdapter(SpringDataWORepo r){ this.repo=r; }
  public WorkOrderId save(WorkOrder wo){
    var e = new JpaWorkOrder();
    e.setId(wo.id().value());
    e.setOrderId(wo.orderId());
    e.setStatus(wo.status().name());
    e.setCreatedAt(java.util.Date.from(wo.createdAt()));
    e.getLines().clear();
    for (var l : wo.lines()){
      var le = new JpaWorkOrderLine();
      try{ var f=JpaWorkOrderLine.class.getDeclaredField("sku"); f.setAccessible(true); f.set(le, l.sku()); }catch(Exception ignore){}
      try{ var f=JpaWorkOrderLine.class.getDeclaredField("quantity"); f.setAccessible(true); f.set(le, l.quantity()); }catch(Exception ignore){}
      le.setWorkOrder(e);
      e.getLines().add(le);
    }
    repo.save(e);
    return wo.id();
  }
  public Optional<WorkOrder> findById(WorkOrderId id){
    return repo.findById(id.value()).map(e -> new WorkOrder(new WorkOrderId(e.getId()), e.getOrderId()));
  }
}
