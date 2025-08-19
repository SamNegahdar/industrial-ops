package com.industrial.inventoryservice.adapters.in.web;
import com.industrial.inventoryservice.application.InventoryUseCases;
import com.industrial.inventoryservice.application.commands.AdjustStockCommand;
import com.industrial.inventoryservice.application.queries.GetStockQuery;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController @RequestMapping("/inventory")
public class InventoryController {
  private final InventoryUseCases useCases;
  public InventoryController(InventoryUseCases uc){ this.useCases=uc; }
  @PostMapping("/adjust")
  public Map<String,String> adjust(@RequestBody Map<String,Object> body){
    var cmd = new AdjustStockCommand((String)body.get("sku"), ((Number)body.get("delta")).intValue());
    useCases.handle(cmd);
    return Map.of("status","ok");
  }
  @GetMapping("/{sku}")
  public Map<String,Integer> get(@PathVariable String sku){
    return Map.of("onHand", useCases.handle(new GetStockQuery(sku)));
  }
}
