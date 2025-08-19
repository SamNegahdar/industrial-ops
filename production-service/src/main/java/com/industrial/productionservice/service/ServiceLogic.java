package com.industrial.productionservice.service;

import com.industrial.common.BusinessException;
import com.industrial.common.ErrorCode;
import com.industrial.productionservice.domain.Domain;
import jakarta.validation.Valid;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceLogic {
  private final Map<UUID, Domain.WorkOrder> store = new ConcurrentHashMap<>();

  public Domain.WorkOrder create(@Valid Domain.WorkOrder wo) {
    wo.setId(UUID.randomUUID());
    wo.setStatus(Domain.WorkOrder.Status.NEW);
    store.put(wo.getId(), wo);
    return wo;
  }
  public Domain.WorkOrder start(UUID id) {
    var wo = get(id);
    if (wo.getStatus()!= Domain.WorkOrder.Status.NEW)
      throw new BusinessException(ErrorCode.BUSINESS_RULE_VIOLATION, "Only NEW can start");
    wo.setStatus(Domain.WorkOrder.Status.IN_PROGRESS);
    return wo;
  }
  public Domain.WorkOrder complete(UUID id) {
    var wo = get(id);
    if (wo.getStatus()!= Domain.WorkOrder.Status.IN_PROGRESS)
      throw new BusinessException(ErrorCode.BUSINESS_RULE_VIOLATION, "Only IN_PROGRESS can be completed");
    wo.setStatus(Domain.WorkOrder.Status.DONE);
    return wo;
  }
  public Domain.WorkOrder cancel(UUID id) {
    var wo = get(id);
    if (wo.getStatus()== Domain.WorkOrder.Status.DONE)
      throw new BusinessException(ErrorCode.CONFLICT, "Cannot cancel DONE");
    wo.setStatus(Domain.WorkOrder.Status.CANCELLED);
    return wo;
  }
  public Domain.WorkOrder get(UUID id) {
    return Optional.ofNullable(store.get(id)).orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "WorkOrder not found"));
  }
  public List<Domain.WorkOrder> list(){ return new ArrayList<>(store.values()); }
}
