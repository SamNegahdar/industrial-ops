package com.industrial.inventoryservice.ports.out;
import com.industrial.inventoryservice.domain.*;
import java.util.Optional;
public interface StockRepository {
  Optional<StockItem> find(SkuId id);
  void save(StockItem item);
}
