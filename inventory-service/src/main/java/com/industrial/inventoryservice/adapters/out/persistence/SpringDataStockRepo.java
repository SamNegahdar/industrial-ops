package com.industrial.inventoryservice.adapters.out.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SpringDataStockRepo extends JpaRepository<JpaStock, String> { }
