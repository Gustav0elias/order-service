package com.ms.ordermicroservice.domain.serviceports;

import com.ms.ordermicroservice.application.enums.StatusOrder;
import com.ms.ordermicroservice.domain.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    public Order createOrder(Order order);
    public Optional<Order> findOrderById(UUID id);
    public List<Order> findAllOrders();
    public Optional<Order> updateOrderStatus(UUID id, StatusOrder status);
}
