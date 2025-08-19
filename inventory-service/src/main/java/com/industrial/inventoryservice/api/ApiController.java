package com.industrial.inventoryservice.api;

import com.industrial.common.ApiResponse;
import com.industrial.inventoryservice.domain.Domain;
import com.industrial.inventoryservice.service.ServiceLogic;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/inventory")
public class ApiController {
  private final ServiceLogic logic = new ServiceLogic();

  @PostMapping("/sku")
  public ApiResponse<Domain.Sku> upsert(@Valid @RequestBody Domain.Sku s) {
    return ApiResponse.ok(logic.upsertSku(s));
  }

  @GetMapping("/sku")
  public ApiResponse<List<Domain.Sku>> list() {
    return ApiResponse.ok(logic.listSkus());
  }

  @PostMapping("/reserve")
  public ApiResponse<Domain.Reservation> reserve(@Valid @RequestBody Domain.Reservation r) {
    return ApiResponse.ok(logic.reserve(r));
  }

  @PostMapping("/reserve/{id}/release")
  public ApiResponse<Void> release(@PathVariable UUID id) {
    logic.release(id);
    return ApiResponse.ok();
  }

  @PostMapping("/reserve/{id}/commit")
  public ApiResponse<Void> commit(@PathVariable UUID id) {
    logic.commit(id);
    return ApiResponse.ok();
  }
}
