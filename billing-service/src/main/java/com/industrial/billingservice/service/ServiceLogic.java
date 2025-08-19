package com.industrial.billingservice.service;

import com.industrial.common.BusinessException;
import com.industrial.common.ErrorCode;
import com.industrial.billingservice.domain.Domain;
import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceLogic {
  private final Map<UUID, Domain.Invoice> invoices = new ConcurrentHashMap<>();

  public Domain.Invoice draft(@Valid Domain.Invoice i){
    recalc(i);
    i.setId(UUID.randomUUID());
    i.setStatus(Domain.Invoice.Status.DRAFT);
    invoices.put(i.getId(), i);
    return i;
  }

  private void recalc(Domain.Invoice i){
    BigDecimal subtotal = BigDecimal.ZERO;
    for (Domain.Line l : i.getLines()){
      subtotal = subtotal.add(l.getUnitPrice().multiply(BigDecimal.valueOf(l.getQty())));
    }
    BigDecimal tax = subtotal.multiply(BigDecimal.valueOf(0.09)).setScale(2, BigDecimal.ROUND_HALF_UP);
    i.setSubtotal(subtotal);
    i.setTax(tax);
    i.setTotal(subtotal.add(tax));
  }

  public Domain.Invoice issue(UUID id){
    var i = get(id);
    if (i.getStatus()!= Domain.Invoice.Status.DRAFT)
      throw new BusinessException(ErrorCode.BUSINESS_RULE_VIOLATION, "Only DRAFT can be issued");
    if (i.getTotal().signum()<=0)
      throw new BusinessException(ErrorCode.VALIDATION_ERROR, "Total must be positive");
    i.setStatus(Domain.Invoice.Status.ISSUED);
    return i;
  }

  public Domain.Invoice pay(UUID id){
    var i = get(id);
    if (i.getStatus()!= Domain.Invoice.Status.ISSUED)
      throw new BusinessException(ErrorCode.BUSINESS_RULE_VIOLATION, "Only ISSUED can be paid");
    i.setStatus(Domain.Invoice.Status.PAID);
    return i;
  }

  public Domain.Invoice cancel(UUID id){
    var i = get(id);
    if (i.getStatus()== Domain.Invoice.Status.PAID)
      throw new BusinessException(ErrorCode.CONFLICT, "Cannot cancel paid invoice");
    i.setStatus(Domain.Invoice.Status.CANCELLED);
    return i;
  }

  public Domain.Invoice get(UUID id){
    return Optional.ofNullable(invoices.get(id)).orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND, "Invoice not found"));
  }
  public List<Domain.Invoice> list(){ return new ArrayList<>(invoices.values()); }
}
