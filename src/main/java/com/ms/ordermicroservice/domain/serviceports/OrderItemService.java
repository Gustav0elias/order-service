package com.ms.ordermicroservice.domain.serviceports;

import com.ms.ordermicroservice.domain.model.OrderItem;

public interface OrderItemService {
    public OrderItem createOrderItem(OrderItem order);
}
