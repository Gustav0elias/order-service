package com.ms.ordermicroservice.domain.model;

import java.util.UUID;

import com.ms.ordermicroservice.application.enums.StatusOrder;

public class OrderStatusUpdatedEvent {
 private UUID orderId;
    private StatusOrder newStatus;
    private String email;

    public OrderStatusUpdatedEvent(UUID orderId, StatusOrder newStatus, String email) {
        this.orderId = orderId;
        this.newStatus = newStatus;
        this.email = email;
    }

    
    public UUID getOrderId() {
        return orderId;
    }

    public StatusOrder getNewStatus() {
        return newStatus;
    }

    public String getEmail() {
        return email;
    }
}
