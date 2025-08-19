package com.industrial.shippingservice.api;

import com.industrial.common.ApiResponse;
import com.industrial.shippingservice.domain.Domain;
import com.industrial.shippingservice.service.ServiceLogic;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shipping")
public class ApiController {
  private final ServiceLogic logic = new ServiceLogic();

  @PostMapping
  public ApiResponse<Domain.Shipment> create(@Valid @RequestBody Domain.Shipment s){ return ApiResponse.ok(logic.create(s)); }
  @PostMapping("{id}/pick")
  public ApiResponse<Domain.Shipment> pick(@PathVariable UUID id){ return ApiResponse.ok(logic.pick(id)); }
  @PostMapping("{id}/transit")
  public ApiResponse<Domain.Shipment> transit(@PathVariable UUID id){ return ApiResponse.ok(logic.transit(id)); }
  @PostMapping("{id}/deliver")
  public ApiResponse<Domain.Shipment> deliver(@PathVariable UUID id){ return ApiResponse.ok(logic.deliver(id)); }
  @PostMapping("{id}/cancel")
  public ApiResponse<Domain.Shipment> cancel(@PathVariable UUID id){ return ApiResponse.ok(logic.cancel(id)); }
  @GetMapping("{id}") public ApiResponse<Domain.Shipment> get(@PathVariable UUID id){ return ApiResponse.ok(logic.get(id)); }
  @GetMapping public ApiResponse<List<Domain.Shipment>> list(){ return ApiResponse.ok(logic.list()); }
}
