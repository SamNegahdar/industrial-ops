package com.industrial.inventoryservice.application.commands;
public record AdjustStockCommand(String sku, int delta) { }
