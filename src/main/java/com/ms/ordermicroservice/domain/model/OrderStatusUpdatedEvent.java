package com.ms.ordermicroservice.domain.model;

import java.util.UUID;

import com.ms.ordermicroservice.application.enums.StatusOrder;

public class OrderStatusUpdatedEvent {
 private UUID orderId;
    private StatusOrder newStatus;

    public OrderStatusUpdatedEvent(UUID orderId, StatusOrder newStatus) {
        this.orderId = orderId;
        this.newStatus = newStatus;
    }

    
    public UUID getOrderId() {
        return orderId;
    }

    public StatusOrder getNewStatus() {
        return newStatus;
    }
}
