package com.industrial.shippingservice.service;

import com.industrial.common.BusinessException;
import com.industrial.common.ErrorCode;
import com.industrial.shippingservice.domain.Domain;
import jakarta.validation.Valid;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceLogic {
  private final Map<UUID, Domain.Shipment> store = new ConcurrentHashMap<>();

  public Domain.Shipment create(@Valid Domain.Shipment s){
    s.setId(UUID.randomUUID());
    s.setStatus(Domain.Shipment.Status.CREATED);
    s.setTracking("TRK-" + Math.abs(new Random().nextInt()));
    store.put(s.getId(), s);
    return s;
  }
  public Domain.Shipment pick(UUID id){
    var s = get(id);
    if (s.getStatus()!= Domain.Shipment.Status.CREATED) throw new BusinessException(ErrorCode.BUSINESS_RULE_VIOLATION,"Only CREATED can be picked");
    s.setStatus(Domain.Shipment.Status.PICKED); return s;
  }
  public Domain.Shipment transit(UUID id){
    var s = get(id);
    if (s.getStatus()!= Domain.Shipment.Status.PICKED) throw new BusinessException(ErrorCode.BUSINESS_RULE_VIOLATION,"Only PICKED can go IN_TRANSIT");
    s.setStatus(Domain.Shipment.Status.IN_TRANSIT); return s;
  }
  public Domain.Shipment deliver(UUID id){
    var s = get(id);
    if (s.getStatus()!= Domain.Shipment.Status.IN_TRANSIT) throw new BusinessException(ErrorCode.BUSINESS_RULE_VIOLATION,"Only IN_TRANSIT can be delivered");
    s.setStatus(Domain.Shipment.Status.DELIVERED); return s;
  }
  public Domain.Shipment cancel(UUID id){
    var s = get(id);
    if (s.getStatus()== Domain.Shipment.Status.DELIVERED) throw new BusinessException(ErrorCode.CONFLICT,"Cannot cancel delivered");
    s.setStatus(Domain.Shipment.Status.CANCELLED); return s;
  }
  public Domain.Shipment get(UUID id){ return Optional.ofNullable(store.get(id)).orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "Shipment not found")); }
  public List<Domain.Shipment> list(){ return new ArrayList<>(store.values()); }
}
