package com.industrial.warehouseservice.api;

import com.industrial.common.ApiResponse;
import com.industrial.warehouseservice.domain.Domain;
import com.industrial.warehouseservice.service.ServiceLogic;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/warehouse")
public class ApiController {
  private final ServiceLogic logic = new ServiceLogic();

  @PostMapping("/move")
  public ApiResponse<Domain.Movement> move(@Valid @RequestBody Domain.Movement m){
    return ApiResponse.ok(logic.move(m));
  }

  @GetMapping("/balance")
  public ApiResponse<Long> balance(@RequestParam String location, @RequestParam String sku){
    return ApiResponse.ok(logic.balance(location, sku));
  }
}
