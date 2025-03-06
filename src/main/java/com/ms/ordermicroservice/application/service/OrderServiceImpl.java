package com.ms.ordermicroservice.application.service;

import com.ms.ordermicroservice.application.dto.response.OrderResponseDTO;
import com.ms.ordermicroservice.application.mapper.OrderMapper;
import com.ms.ordermicroservice.domain.model.Order;
import com.ms.ordermicroservice.domain.repositoryports.OrderRepository;
import com.ms.ordermicroservice.domain.serviceports.OrderService;

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



}
