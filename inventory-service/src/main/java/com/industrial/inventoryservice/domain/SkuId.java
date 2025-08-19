package com.industrial.inventoryservice.domain;
public record SkuId(String value) { public SkuId{ if(value==null||value.isBlank()) throw new IllegalArgumentException(); } }
