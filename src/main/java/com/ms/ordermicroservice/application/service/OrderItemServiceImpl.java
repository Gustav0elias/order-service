package com.ms.ordermicroservice.application.service;

import com.ms.ordermicroservice.domain.model.OrderItem;
import com.ms.ordermicroservice.domain.repositoryports.OrderItemRepository;
import com.ms.ordermicroservice.domain.serviceports.OrderItemService;

public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository repository;

    public OrderItemServiceImpl(OrderItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrderItem createOrderItem(OrderItem order) {
        return repository.createOrderItem(order);
    }
}
