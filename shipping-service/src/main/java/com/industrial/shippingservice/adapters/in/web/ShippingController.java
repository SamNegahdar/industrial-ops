package com.industrial.shippingservice.adapters.in.web;
import com.industrial.shippingservice.application.*;
import com.industrial.shippingservice.application.commands.*;
import com.industrial.shippingservice.application.queries.GetShipmentQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/shipping")
public class ShippingController {
  private final ShippingUseCases uc;
  public ShippingController(ShippingUseCases u){ this.uc=u; }

  @PostMapping("/shipments")
  public Map<String,String> create(@RequestBody Map<String,Object> b){
    var id = uc.handle(new CreateShipmentCommand((String)b.get("orderId")));
    return Map.of("shipmentId", id.value().toString());
  }
  @PostMapping("/shipments/{id}/assign")
  public ResponseEntity<?> assign(@PathVariable String id, @RequestBody Map<String,Object> b){
    uc.handle(new AssignCarrierCommand(id, (String)b.get("carrier"), (String)b.get("tracking")));
    return ResponseEntity.noContent().build();
  }
  @PostMapping("/shipments/{id}/in-transit")
  public ResponseEntity<?> inTransit(@PathVariable String id){
    uc.handle(new MarkInTransitCommand(id)); return ResponseEntity.noContent().build();
  }
  @PostMapping("/shipments/{id}/delivered")
  public ResponseEntity<?> delivered(@PathVariable String id){
    uc.handle(new MarkDeliveredCommand(id)); return ResponseEntity.noContent().build();
  }
  @GetMapping("/shipments/{id}")
  public Object get(@PathVariable String id){
    var s = uc.handle(new GetShipmentQuery(id));
    return Map.of("id", s.id().value().toString(), "orderId", s.orderId(), "status", s.status().name(), "carrier", s.carrier(), "tracking", s.tracking());
  }
}
