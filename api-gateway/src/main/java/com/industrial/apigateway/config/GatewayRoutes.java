package com.industrial.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutes {

  @Value("${services.order:http://localhost:8081}")
  private String orderService;

  @Value("${services.inventory:http://localhost:8083}")
  private String inventoryService;

  @Value("${services.production:http://localhost:8084}")
  private String productionService;

  @Value("${services.warehouse:http://localhost:8085}")
  private String warehouseService;

  @Value("${services.shipping:http://localhost:8086}")
  private String shippingService;

  @Value("${services.billing:http://localhost:8087}")
  private String billingService;

  @Value("${services.notification:http://localhost:8088}")
  private String notificationService;

  @Value("${services.chat:http://localhost:8089}")
  private String chatService;

  @Bean
  public RouteLocator routeLocator(RouteLocatorBuilder b) {
    return b.routes()
        .route("order", r -> r.path("/order/**").filters(f -> f.stripPrefix(1)).uri(orderService))
        .route("inventory", r -> r.path("/inventory/**").filters(f -> f.stripPrefix(1)).uri(inventoryService))
        .route("production", r -> r.path("/production/**").filters(f -> f.stripPrefix(1)).uri(productionService))
        .route("warehouse", r -> r.path("/warehouse/**").filters(f -> f.stripPrefix(1)).uri(warehouseService))
        .route("shipping", r -> r.path("/shipping/**").filters(f -> f.stripPrefix(1)).uri(shippingService))
        .route("billing", r -> r.path("/billing/**").filters(f -> f.stripPrefix(1)).uri(billingService))
        .route("notification", r -> r.path("/notification/**").filters(f -> f.stripPrefix(1)).uri(notificationService))
        .route("chat", r -> r.path("/chat/**").filters(f -> f.stripPrefix(1)).uri(chatService))
        .build();
  }
}
