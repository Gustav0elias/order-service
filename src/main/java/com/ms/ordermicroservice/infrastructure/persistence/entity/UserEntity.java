package com.ms.ordermicroservice.infrastructure.persistence.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="user_tb")

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String email;
    @Column(name = "created_at", updatable = false)
    private final LocalDate createdAt = LocalDate.now();
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderEntity> orders;

    public UserEntity() {
    }

    public UserEntity(UUID id, String name, String email, List<OrderEntity> orders) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.orders = orders;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }
}
