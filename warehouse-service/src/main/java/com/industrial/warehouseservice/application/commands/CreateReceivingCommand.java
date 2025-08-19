package com.industrial.warehouseservice.application.commands;
public record CreateReceivingCommand(String asnId, String sku, int quantity){}
