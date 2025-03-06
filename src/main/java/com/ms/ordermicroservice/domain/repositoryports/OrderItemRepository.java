package com.ms.ordermicroservice.domain.repositoryports;

import com.ms.ordermicroservice.domain.model.OrderItem;

public interface OrderItemRepository {
    public OrderItem createOrderItem(OrderItem order);

}
