package com.ms.ordermicroservice.infrastructure.persistence.repositoryjpa;

import com.ms.ordermicroservice.infrastructure.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepositoryJpa   extends JpaRepository<OrderEntity, UUID> {
}
