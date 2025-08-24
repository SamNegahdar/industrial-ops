package com.ios.order.application.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record CreateOrderRequest(
        @NotBlank String customerId,
        @NotEmpty List<Line> lines,
        @NotBlank String currency,
        @Positive BigDecimal total,
        @NotNull Instant slaDueAt) {

    public record Line(@NotBlank String sku, @Positive int qty) {
    }
}
