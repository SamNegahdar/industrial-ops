package com.industrial.warehouseservice.service;

import com.industrial.common.BusinessException;
import com.industrial.common.ErrorCode;
import com.industrial.warehouseservice.domain.Domain;
import jakarta.validation.Valid;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceLogic {
  // key: location|sku
  private final Map<String, Long> balances = new ConcurrentHashMap<>();
  private String key(String loc, String sku){ return loc + "|" + sku; }

  public Domain.Movement move(@Valid Domain.Movement m){
    if (m.getType()==null) throw new com.industrial.common.BusinessException(ErrorCode.VALIDATION_ERROR, "type required");
    m.setId(UUID.randomUUID());
    String k = key(m.getLocation(), m.getSku());
    balances.putIfAbsent(k, 0L);
    if (m.getType()== Domain.Movement.Type.OUTBOUND) {
      long current = balances.get(k);
      if (current < m.getQty())
        throw new BusinessException(ErrorCode.BUSINESS_RULE_VIOLATION, "Insufficient stock at location");
      balances.put(k, current - m.getQty());
    } else {
      balances.put(k, balances.get(k) + m.getQty());
    }
    return m;
  }

  public long balance(String location, String sku){
    return balances.getOrDefault(key(location, sku), 0L);
  }
}
