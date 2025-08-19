package com.industrial.productionservice.api;

import com.industrial.common.ApiResponse;
import com.industrial.productionservice.domain.Domain;
import com.industrial.productionservice.service.ServiceLogic;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/production/work-orders")
public class ApiController {

  private final ServiceLogic logic = new ServiceLogic();

  @PostMapping
  public ApiResponse<Domain.WorkOrder> create(@Valid @RequestBody Domain.WorkOrder wo){
    return ApiResponse.ok(logic.create(wo));
  }
  @PostMapping("{id}/start")
  public ApiResponse<Domain.WorkOrder> start(@PathVariable UUID id){
    return ApiResponse.ok(logic.start(id));
  }
  @PostMapping("{id}/complete")
  public ApiResponse<Domain.WorkOrder> complete(@PathVariable UUID id){
    return ApiResponse.ok(logic.complete(id));
  }
  @PostMapping("{id}/cancel")
  public ApiResponse<Domain.WorkOrder> cancel(@PathVariable UUID id){
    return ApiResponse.ok(logic.cancel(id));
  }
  @GetMapping("{id}")
  public ApiResponse<Domain.WorkOrder> get(@PathVariable UUID id){ return ApiResponse.ok(logic.get(id)); }
  @GetMapping
  public ApiResponse<List<Domain.WorkOrder>> list() { return ApiResponse.ok(logic.list()); }
}
