package com.industrial.inventoryservice.adapters.out.persistence;
import com.industrial.inventoryservice.domain.*;
import com.industrial.inventoryservice.ports.out.StockRepository;
import org.springframework.stereotype.Component;
@Component
public class StockRepositoryAdapter implements StockRepository {
  private final SpringDataStockRepo repo;
  public StockRepositoryAdapter(SpringDataStockRepo r){ this.repo=r; }
  public java.util.Optional<StockItem> find(SkuId id){
    return repo.findById(id.value()).map(e -> new StockItem(new SkuId(e.getSku()), e.getOnHand()));
  }
  public void save(StockItem i){
    var e = new JpaStock(); e.setSku(i.sku().value()); e.setOnHand(i.onHand()); repo.save(e);
  }
}
