package com.industrial.shippingservice.domain;
import java.util.UUID;
public record ShipmentId(UUID value){ public static ShipmentId newId(){ return new ShipmentId(UUID.randomUUID()); } }
