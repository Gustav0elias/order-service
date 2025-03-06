package com.ms.ordermicroservice.infrastructure.persistence.repositoryjpa;

import com.ms.ordermicroservice.infrastructure.persistence.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepositoryJpa extends JpaRepository<OrderItemEntity, UUID> {
}
