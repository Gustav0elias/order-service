package com.ms.ordermicroservice.domain.repositoryports;

import com.ms.ordermicroservice.domain.model.Order;
import com.ms.ordermicroservice.domain.model.OrderItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    public Order createOrder(Order order);
    public Optional<Order> findOrderById(UUID id);
    public List<Order> findAllOrders();
    public Optional<Order> updateOrderStatus(UUID id, Order order);
     public Order addItemToOrder (UUID orderId, OrderItem orderItem);
     public Order removeItemFromOrder (UUID orderId, UUID itemId);
}
