package com.industrial.warehouseservice.adapters.in.web;
import com.industrial.warehouseservice.application.*;
import com.industrial.warehouseservice.application.commands.*;
import com.industrial.warehouseservice.application.queries.GetTaskQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/warehouse")
public class WarehouseController {
  private final WarehouseUseCases uc;
  public WarehouseController(WarehouseUseCases u){ this.uc=u; }

  @PostMapping("/receivings")
  public Map<String,String> createReceiving(@RequestBody Map<String,Object> b){
    var id = uc.handle(new CreateReceivingCommand((String)b.get("asnId"), (String)b.get("sku"), ((Number)b.get("quantity")).intValue()));
    return Map.of("taskId", id.value().toString());
  }

  @PostMapping("/picks")
  public Map<String,String> createPick(@RequestBody Map<String,Object> b){
    var id = uc.handle(new CreatePickCommand((String)b.get("orderId"), (String)b.get("sku"), ((Number)b.get("quantity")).intValue()));
    return Map.of("taskId", id.value().toString());
  }

  @PostMapping("/tasks/{id}/start")
  public ResponseEntity<?> start(@PathVariable String id){ uc.handle(new StartTaskCommand(id)); return ResponseEntity.noContent().build(); }

  @PostMapping("/tasks/{id}/complete")
  public ResponseEntity<?> complete(@PathVariable String id){ uc.handle(new CompleteTaskCommand(id)); return ResponseEntity.noContent().build(); }

  @GetMapping("/tasks/{id}")
  public Object get(@PathVariable String id){
    var t = uc.handle(new GetTaskQuery(id));
    return Map.of("id", t.id().value().toString(), "type", t.type().name(), "ref", t.ref(), "sku", t.sku(), "qty", t.quantity(), "status", t.status().name());
  }
}
