package com.industrial.warehouseservice.adapters.out.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface SpringDataTaskRepo extends JpaRepository<JpaTask, UUID>{}
