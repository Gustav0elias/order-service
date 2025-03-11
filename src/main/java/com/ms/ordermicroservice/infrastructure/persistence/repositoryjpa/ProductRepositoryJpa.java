package com.ms.ordermicroservice.infrastructure.persistence.repositoryjpa;
import com.ms.ordermicroservice.infrastructure.persistence.entity.ProductEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepositoryJpa  extends JpaRepository<ProductEntity, UUID> {
    Page<ProductEntity> findAll (Pageable pageable);

    Optional<ProductEntity> findProductByName(String name);
}
