package com.ms.ordermicroservice.infrastructure.persistence.repositoryjpa;

import com.ms.ordermicroservice.infrastructure.persistence.entity.OrderEntity;
import com.ms.ordermicroservice.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRepositoryJpa extends JpaRepository<UserEntity, UUID> {
    UserEntity findByEmail(String email);
    @Query("SELECT u.orders FROM UserEntity u WHERE u.id = :userId")
    Page<OrderEntity> findOrdersByUserId(@Param("userId") UUID userId, Pageable pageable);
}
