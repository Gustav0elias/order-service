package com.ms.ordermicroservice.infrastructure.persistence.repositoryjpa;
import com.ms.ordermicroservice.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepositoryJpa  extends JpaRepository<ProductEntity, UUID> {
}
