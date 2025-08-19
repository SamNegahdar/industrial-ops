package com.industrial.billingservice.api;

import com.industrial.common.ApiResponse;
import com.industrial.billingservice.domain.Domain;
import com.industrial.billingservice.service.ServiceLogic;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/billing/invoices")
public class ApiController {
  private final ServiceLogic logic = new ServiceLogic();

  @PostMapping
  public ApiResponse<Domain.Invoice> draft(@Valid @RequestBody Domain.Invoice i){ return ApiResponse.ok(logic.draft(i)); }
  @PostMapping("{id}/issue")
  public ApiResponse<Domain.Invoice> issue(@PathVariable UUID id){ return ApiResponse.ok(logic.issue(id)); }
  @PostMapping("{id}/pay")
  public ApiResponse<Domain.Invoice> pay(@PathVariable UUID id){ return ApiResponse.ok(logic.pay(id)); }
  @PostMapping("{id}/cancel")
  public ApiResponse<Domain.Invoice> cancel(@PathVariable UUID id){ return ApiResponse.ok(logic.cancel(id)); }
  @GetMapping("{id}") public ApiResponse<Domain.Invoice> get(@PathVariable UUID id){ return ApiResponse.ok(logic.get(id)); }
  @GetMapping public ApiResponse<List<Domain.Invoice>> list(){ return ApiResponse.ok(logic.list()); }
}
