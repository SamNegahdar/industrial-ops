package com.industrial.warehouseservice.adapters.out.persistence;
import jakarta.persistence.*;
import java.util.*;
@Entity @Table(name="wh_tasks")
public class JpaTask {
  @Id private UUID id;
  private String type;
  private String ref;
  private String sku;
  private int quantity;
  private String status;
  private Date createdAt;
  public UUID getId(){return id;} public void setId(UUID id){this.id=id;}
  public String getType(){return type;} public void setType(String t){this.type=t;}
  public String getRef(){return ref;} public void setRef(String r){this.ref=r;}
  public String getSku(){return sku;} public void setSku(String s){this.sku=s;}
  public int getQuantity(){return quantity;} public void setQuantity(int q){this.quantity=q;}
  public String getStatus(){return status;} public void setStatus(String s){this.status=s;}
  public Date getCreatedAt(){return createdAt;} public void setCreatedAt(Date d){this.createdAt=d;}
}
