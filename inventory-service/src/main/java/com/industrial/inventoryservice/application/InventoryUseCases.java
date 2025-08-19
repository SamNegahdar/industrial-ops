package com.industrial.inventoryservice.application;
import com.industrial.inventoryservice.application.commands.AdjustStockCommand;
import com.industrial.inventoryservice.application.queries.GetStockQuery;
import com.industrial.inventoryservice.domain.*;
import com.industrial.inventoryservice.ports.out.StockRepository;

public class InventoryUseCases {
  private final StockRepository repo;
  public InventoryUseCases(StockRepository repo){ this.repo=repo; }
  public void handle(AdjustStockCommand cmd){
    var sku = new SkuId(cmd.sku());
    var item = repo.find(sku).orElseGet(() -> new StockItem(sku,0));
    if (cmd.delta()>=0) item.add(cmd.delta());
    else if (!item.reserve(-cmd.delta())) throw new IllegalStateException("insufficient");
    repo.save(item);
  }
  public int handle(GetStockQuery q){
    return repo.find(new SkuId(q.sku())).map(StockItem::onHand).orElse(0);
  }
}
