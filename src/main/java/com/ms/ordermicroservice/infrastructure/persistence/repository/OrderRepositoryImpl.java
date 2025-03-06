package com.ms.ordermicroservice.infrastructure.persistence.repository;

import com.ms.ordermicroservice.domain.model.Order;
import com.ms.ordermicroservice.domain.model.OrderItem;
import com.ms.ordermicroservice.domain.repositoryports.OrderItemRepository;
import com.ms.ordermicroservice.domain.repositoryports.OrderRepository;
import com.ms.ordermicroservice.domain.serviceports.OrderItemService;
import com.ms.ordermicroservice.infrastructure.persistence.entity.OrderEntity;
import com.ms.ordermicroservice.infrastructure.persistence.entity.OrderItemEntity;
import com.ms.ordermicroservice.infrastructure.persistence.repositoryjpa.OrderRepositoryJpa;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderRepositoryImpl implements OrderRepository {
    private final OrderRepositoryJpa orderRepositoryJpa;
    private final ModelMapper modelMapper;

    public OrderRepositoryImpl(OrderRepositoryJpa orderRepositoryJpa, ModelMapper modelMapper) {
        this.orderRepositoryJpa = orderRepositoryJpa;
        this.modelMapper = modelMapper;

    }

    @Override
    public Order createOrder(Order order) {

        List<OrderItemEntity> orderItemEntities = order.getItems().stream()
                .map(item -> modelMapper.map(item, OrderItemEntity.class))
                .collect(Collectors.toList());

        OrderEntity orderEntity = modelMapper.map(order, OrderEntity.class);
        orderEntity.setItems(orderItemEntities);

        orderItemEntities.forEach(item -> item.setOrder(orderEntity));

        OrderEntity savedOrderEntity = orderRepositoryJpa.save(orderEntity);

        return modelMapper.map(savedOrderEntity, Order.class);
    }


    @Override
    public Optional<Order> findOrderById(UUID id) {
        return orderRepositoryJpa.findById(id).map(o->modelMapper.map(o, Order.class));
    }

    @Override
    public List<Order> findAllOrders() {
        List<OrderEntity> orderEntities = orderRepositoryJpa.findAll();
        return orderEntities.stream().map(o->modelMapper.map(o, Order.class)).toList();
    }


}
