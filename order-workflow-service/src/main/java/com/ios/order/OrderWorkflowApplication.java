package com.ios.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class OrderWorkflowApplication {
  public static void main(String[] args) {
    SpringApplication.run(OrderWorkflowApplication.class, args);
  }
}
