package com.industrial.productionservice.domain;
import java.util.UUID;
public record WorkOrderId(UUID value){ public static WorkOrderId newId(){ return new WorkOrderId(UUID.randomUUID()); } }
