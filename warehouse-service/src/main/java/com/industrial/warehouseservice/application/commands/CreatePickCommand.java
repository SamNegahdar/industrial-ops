package com.industrial.warehouseservice.application.commands;
public record CreatePickCommand(String orderId, String sku, int quantity){}
