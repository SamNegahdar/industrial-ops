package com.industrial.inventoryservice.domain;
public class StockItem {
  private final SkuId sku;
  private int onHand;
  public StockItem(SkuId sku, int onHand){ this.sku=sku; this.onHand=onHand; }
  public void add(int qty){ if(qty<=0) throw new IllegalArgumentException(); onHand+=qty; }
  public boolean reserve(int qty){ if(qty<=0) throw new IllegalArgumentException(); if(onHand>=qty){ onHand-=qty; return true;} return false; }
  public int onHand(){ return onHand; }
  public SkuId sku(){ return sku; }
}
