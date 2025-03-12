package com.ms.ordermicroservice.application.service;

import com.ms.ordermicroservice.application.dto.response.OrderResponseDTO;
import com.ms.ordermicroservice.application.enums.StatusOrder;
import com.ms.ordermicroservice.application.mapper.OrderMapper;
import com.ms.ordermicroservice.domain.model.Order;
import com.ms.ordermicroservice.domain.model.OrderItem;
import com.ms.ordermicroservice.domain.repositoryports.OrderRepository;
import com.ms.ordermicroservice.domain.serviceports.OrderService;
import com.ms.ordermicroservice.domain.serviceports.ProductService;
import com.ms.ordermicroservice.infrastructure.web.exception.ResourceNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
            this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {

        return orderRepository.createOrder(order);
    }

    @Override
    public Optional<Order> findOrderById(UUID id) {

        return orderRepository.findOrderById(id);
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAllOrders();
    }

    @Override
    public Optional<Order> updateOrderStatus(UUID id, StatusOrder status) {
    Order existingOrder = orderRepository.findOrderById(id)
        .orElseThrow(()-> new ResourceNotFoundException("Pedido não encontrado") );
        existingOrder.setStatus(status);
      return orderRepository.updateOrderStatus(id, existingOrder);
    }

    @Override
    public Order addItemToOrder(UUID orderId, OrderItem orderItem) {
      
        return orderRepository.addItemToOrder(orderId, orderItem);
    }

    @Override
    public Order removeItemFromOrder(UUID orderId, UUID itemId) {
    
       return orderRepository.removeItemFromOrder(orderId, itemId);
    }



}
