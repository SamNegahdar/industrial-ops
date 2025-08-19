package com.industrial.productionservice.adapters.in.web;
import com.industrial.productionservice.application.*;
import com.industrial.productionservice.application.commands.*;
import com.industrial.productionservice.application.queries.GetWorkOrderQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/production")
public class ProductionController {
  private final ProductionUseCases useCases;
  public ProductionController(ProductionUseCases u){ this.useCases=u; }

  @PostMapping("/workorders")
  public Map<String,String> create(@RequestBody Map<String,Object> body){
    var orderId = (String) body.get("orderId");
    var itemsList = (List<Map<String,Object>>) body.getOrDefault("items", List.of());
    var items = itemsList.stream().map(i -> new CreateWorkOrderCommand.Item(
      (String)i.get("sku"), ((Number)i.get("quantity")).intValue()
    )).toList();
    var id = useCases.handle(new CreateWorkOrderCommand(orderId, items));
    return Map.of("workOrderId", id.value().toString());
  }

  @PostMapping("/workorders/{id}/start")
  public ResponseEntity<?> start(@PathVariable String id){ useCases.handle(new StartWorkOrderCommand(id)); return ResponseEntity.noContent().build(); }

  @PostMapping("/workorders/{id}/complete")
  public ResponseEntity<?> complete(@PathVariable String id){ useCases.handle(new CompleteWorkOrderCommand(id)); return ResponseEntity.noContent().build(); }

  @GetMapping("/workorders/{id}")
  public Object get(@PathVariable String id){
    var wo = useCases.handle(new GetWorkOrderQuery(id));
    return Map.of("id", wo.id().value().toString(), "orderId", wo.orderId(), "status", wo.status().name());
  }
}
