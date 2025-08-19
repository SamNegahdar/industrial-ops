package com.industrial.productionservice.domain;
public record WorkOrderLine(String sku, int quantity){
  public WorkOrderLine {
    if(sku==null||sku.isBlank()) throw new IllegalArgumentException("SKU");
    if(quantity<=0) throw new IllegalArgumentException("qty>0");
  }
}
