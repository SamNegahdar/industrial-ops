package com.industrial.warehouseservice;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/ping")
public class PingController {
  @GetMapping public String ping() { return "warehouse-service:ok"; }
}
