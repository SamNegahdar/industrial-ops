package com.industrial.warehouseservice.domain;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public class Domain {
  public static class Location {
    @NotBlank private String code;
    public String getCode(){ return code; }
    public void setCode(String code){ this.code = code; }
  }
  public static class Movement {
    private UUID id;
    @NotBlank private String sku;
    @NotBlank private String location;
    @Min(1) private long qty;
    private Type type;
    public enum Type { INBOUND, OUTBOUND }

    public UUID getId(){ return id; }
    public String getSku(){ return sku; }
    public String getLocation(){ return location; }
    public long getQty(){ return qty; }
    public Type getType(){ return type; }
    public void setId(UUID id){ this.id = id; }
    public void setSku(String sku){ this.sku = sku; }
    public void setLocation(String location){ this.location = location; }
    public void setQty(long qty){ this.qty = qty; }
    public void setType(Type type){ this.type = type; }
  }
}
