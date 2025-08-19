package com.ios.order.application.dto;

import com.ios.order.domain.model.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record ChangeStatusRequest(@NotNull OrderStatus targetStatus) {}
