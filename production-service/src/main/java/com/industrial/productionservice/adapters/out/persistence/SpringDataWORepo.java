package com.industrial.productionservice.adapters.out.persistence;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface SpringDataWORepo extends JpaRepository<JpaWorkOrder, UUID>{}
