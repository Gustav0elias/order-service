package com.ms.ordermicroservice.domain.repositoryports;

import com.ms.ordermicroservice.domain.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    public Order createOrder(Order order);
    public Optional<Order> findOrderById(UUID id);
    public List<Order> findAllOrders();
}
