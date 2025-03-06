package com.ms.ordermicroservice.infrastructure.persistence.repository;

import com.ms.ordermicroservice.domain.model.OrderItem;
import com.ms.ordermicroservice.domain.repositoryports.OrderItemRepository;
import com.ms.ordermicroservice.infrastructure.persistence.entity.OrderItemEntity;
import com.ms.ordermicroservice.infrastructure.persistence.repositoryjpa.OrderItemRepositoryJpa;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderItemRepositoryImpl implements OrderItemRepository {
    private final OrderItemRepositoryJpa orderItemRepositoryJpa;
    private final ModelMapper modelMapper;

    public OrderItemRepositoryImpl(OrderItemRepositoryJpa orderItemRepositoryJpa, ModelMapper modelMapper) {
        this.orderItemRepositoryJpa = orderItemRepositoryJpa;
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderItem createOrderItem(OrderItem order) {
        OrderItemEntity orderItemEntity = modelMapper.map(order, OrderItemEntity.class);
        OrderItemEntity savedOrderItemEntity = orderItemRepositoryJpa.save(orderItemEntity);
        return modelMapper.map(savedOrderItemEntity, OrderItem.class);
    }
}
