package com.ms.ordermicroservice.domain.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String email;
    private final LocalDate createdAt = LocalDate.now();
    private List<Order> orders;

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

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
