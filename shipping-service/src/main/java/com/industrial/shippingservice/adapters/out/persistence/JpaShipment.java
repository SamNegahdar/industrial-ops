package com.industrial.shippingservice.adapters.out.persistence;
import jakarta.persistence.*;
import java.util.*;
@Entity @Table(name="shipments")
public class JpaShipment {
  @Id private UUID id;
  private String orderId;
  private String carrier;
  private String tracking;
  private String status;
  private Date createdAt;
  public UUID getId(){return id;} public void setId(UUID id){this.id=id;}
  public String getOrderId(){return orderId;} public void setOrderId(String o){this.orderId=o;}
  public String getCarrier(){return carrier;} public void setCarrier(String c){this.carrier=c;}
  public String getTracking(){return tracking;} public void setTracking(String t){this.tracking=t;}
  public String getStatus(){return status;} public void setStatus(String s){this.status=s;}
  public Date getCreatedAt(){return createdAt;} public void setCreatedAt(Date d){this.createdAt=d;}
}
