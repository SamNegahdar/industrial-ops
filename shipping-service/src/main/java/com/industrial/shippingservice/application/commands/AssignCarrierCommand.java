package com.industrial.shippingservice.application.commands;
public record AssignCarrierCommand(String shipmentId, String carrier, String tracking){}
