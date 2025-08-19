package com.industrial.productionservice.application.commands;
import java.util.List;
public record CreateWorkOrderCommand(String orderId, List<Item> items){
  public record Item(String sku, int quantity){}
}
