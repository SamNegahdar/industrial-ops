package com.industrial.inventoryservice.service;

import com.industrial.common.BusinessException;
import com.industrial.common.ErrorCode;
import com.industrial.inventoryservice.domain.Domain;
import jakarta.validation.Valid;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceLogic {

  private final Map<String, Domain.Sku> stock = new ConcurrentHashMap<>();
  private final Map<UUID, Domain.Reservation> reservations = new ConcurrentHashMap<>();

  public Domain.Sku upsertSku(@Valid Domain.Sku s) {
    stock.compute(s.getCode(), (k, old) -> {
      if (old==null) {
        Domain.Sku n = new Domain.Sku();
        n.setCode(s.getCode());
        n.setAvailable(s.getAvailable());
        n.setReserved(0);
        return n;
      } else {
        if (s.getAvailable() < old.getReserved())
          throw new BusinessException(ErrorCode.CONFLICT, "Available cannot fall below reserved.");
        old.setAvailable(s.getAvailable());
        return old;
      }
    });
    return stock.get(s.getCode());
  }

  public List<Domain.Sku> listSkus() { return new ArrayList<>(stock.values()); }

  public Domain.Reservation reserve(@Valid Domain.Reservation r) {
    Domain.Sku sku = stock.get(r.getSku());
    if (sku==null) throw new BusinessException(ErrorCode.NOT_FOUND, "SKU not found");
    if (r.getQty() <= 0) throw new BusinessException(ErrorCode.VALIDATION_ERROR, "qty must be > 0");
    if (sku.getAvailable() - sku.getReserved() < r.getQty())
      throw new BusinessException(ErrorCode.BUSINESS_RULE_VIOLATION, "Insufficient free stock");
    sku.setReserved(sku.getReserved() + r.getQty());
    r.setId(UUID.randomUUID());
    reservations.put(r.getId(), r);
    return r;
  }

  public void release(UUID resId) {
    var r = reservations.remove(resId);
    if (r==null) throw new BusinessException(ErrorCode.NOT_FOUND, "Reservation not found");
    Domain.Sku sku = stock.get(r.getSku());
    sku.setReserved(Math.max(0, sku.getReserved() - r.getQty()));
  }

  public void commit(UUID resId) {
    var r = reservations.remove(resId);
    if (r==null) throw new BusinessException(ErrorCode.NOT_FOUND, "Reservation not found");
    Domain.Sku sku = stock.get(r.getSku());
    if (sku.getReserved() < r.getQty()) throw new BusinessException(ErrorCode.CONFLICT, "Reserved less than qty");
    if (sku.getAvailable() < r.getQty()) throw new BusinessException(ErrorCode.CONFLICT, "Available less than qty");
    sku.setReserved(sku.getReserved() - r.getQty());
    sku.setAvailable(sku.getAvailable() - r.getQty());
  }
}
