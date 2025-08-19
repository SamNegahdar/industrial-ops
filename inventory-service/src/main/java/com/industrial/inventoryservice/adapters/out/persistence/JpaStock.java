package com.industrial.inventoryservice.adapters.out.persistence;
import jakarta.persistence.*;
@Entity @Table(name="stock")
public class JpaStock {
  @Id
  private String sku;
  private int onHand;
  public String getSku(){return sku;}
  public void setSku(String s){this.sku=s;}
  public int getOnHand(){return onHand;}
  public void setOnHand(int v){this.onHand=v;}
}
