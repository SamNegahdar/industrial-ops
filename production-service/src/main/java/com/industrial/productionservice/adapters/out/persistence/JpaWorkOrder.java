package com.industrial.productionservice.adapters.out.persistence;
import jakarta.persistence.*;
import java.util.*;
@Entity @Table(name="work_orders")
public class JpaWorkOrder {
  @Id
  private UUID id;
  private String orderId;
  private String status;
  private Date createdAt;
  @OneToMany(mappedBy="workOrder", cascade=CascadeType.ALL, orphanRemoval=true, fetch=FetchType.EAGER)
  private List<JpaWorkOrderLine> lines = new ArrayList<>();
  public UUID getId(){return id;} public void setId(UUID id){this.id=id;}
  public String getOrderId(){return orderId;} public void setOrderId(String o){this.orderId=o;}
  public String getStatus(){return status;} public void setStatus(String s){this.status=s;}
  public Date getCreatedAt(){return createdAt;} public void setCreatedAt(Date d){this.createdAt=d;}
  public List<JpaWorkOrderLine> getLines(){return lines;}
}
@Entity @Table(name="work_order_lines")
class JpaWorkOrderLine {
  @Id @GeneratedValue
  private Long id;
  private String sku;
  private int quantity;
  @ManyToOne @JoinColumn(name="work_order_id")
  private JpaWorkOrder workOrder;
  public void setWorkOrder(JpaWorkOrder w){ this.workOrder=w; }
}
