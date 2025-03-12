package com.ms.ordermicroservice.infrastructure.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.ms.ordermicroservice.application.dto.response.OrderStatusChangeEventDTO;
import com.ms.ordermicroservice.domain.model.OrderStatusUpdatedEvent;
@Component
public class OrderStatusUpdatedEventListener {
    private final RabbitTemplate rabbitTemplate;

    public OrderStatusUpdatedEventListener(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @EventListener 
    public void handleOrderStatusUpdatedEvent(OrderStatusUpdatedEvent event) {

        rabbitTemplate.convertAndSend(
            RabbitMqConfig.ORDER_EXCHANGE,
            RabbitMqConfig.ORDER_ROUTING_KEY,
            new OrderStatusChangeEventDTO(event.getOrderId(), event.getNewStatus(), event.getEmail())
        );

    }
}