package com.ms.ordermicroservice.infrastructure.persistence.repository;

import com.ms.ordermicroservice.domain.model.Order;
import com.ms.ordermicroservice.domain.model.OrderItem;
import com.ms.ordermicroservice.domain.repositoryports.OrderItemRepository;
import com.ms.ordermicroservice.domain.repositoryports.OrderRepository;
import com.ms.ordermicroservice.domain.serviceports.OrderItemService;
import com.ms.ordermicroservice.infrastructure.persistence.entity.OrderEntity;
import com.ms.ordermicroservice.infrastructure.persistence.entity.OrderItemEntity;
import com.ms.ordermicroservice.infrastructure.persistence.repositoryjpa.OrderRepositoryJpa;
import com.ms.ordermicroservice.infrastructure.web.exception.ResourceNotFoundException;

import jakarta.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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

    @Override
    public Optional<Order> updateOrderStatus(UUID id, Order order) {
     return orderRepositoryJpa.findById(id).map(existingOrder->{
        OrderEntity updatedOrderEntity = modelMapper.map(order, OrderEntity.class);
        updatedOrderEntity.setStatus(order.getStatus());
        return modelMapper.map(orderRepositoryJpa.save(updatedOrderEntity), Order.class);
     });
    }

    @Transactional
    @Override
    public Order addItemToOrder(UUID orderId, OrderItem orderItem) {
        OrderEntity existingOrder = orderRepositoryJpa.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Ordem não encontrada com o ID: " + orderId));
    
        OrderItemEntity orderItemEntity = modelMapper.map(orderItem, OrderItemEntity.class);
        orderItemEntity.setOrder(existingOrder); 
    
        existingOrder.getItems().add(orderItemEntity);
        existingOrder.setTotalAmount(existingOrder.getTotalAmount()
                .add(orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()))));
    
        return modelMapper.map(orderRepositoryJpa.save(existingOrder), Order.class);
    }

    @Override
    public Order removeItemFromOrder(UUID orderId, UUID itemId) {
        OrderEntity existingOrder = orderRepositoryJpa.findById(orderId)
        .orElseThrow(() -> new ResourceNotFoundException("Ordem não encontrada com o ID: " + orderId));
        OrderItemEntity itemToRemove = existingOrder.getItems().stream().
        filter(item -> item.getId().equals(itemId)).findFirst()
        .orElseThrow(() -> new ResourceNotFoundException("Item não encontrado com o ID: " + itemId));

        existingOrder.getItems().remove(itemToRemove);
        existingOrder.setTotalAmount(existingOrder.getTotalAmount().
        subtract(itemToRemove.getPrice().multiply(BigDecimal.valueOf(itemToRemove.getQuantity()))));
        orderRepositoryJpa.save(existingOrder);
        return modelMapper.map(existingOrder, Order.class);
    }
    


}
