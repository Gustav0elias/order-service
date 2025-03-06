package com.ms.ordermicroservice.application.mapper;

import com.ms.ordermicroservice.application.dto.request.OrderRequestDTO;
import com.ms.ordermicroservice.application.dto.response.OrderResponseDTO;
import com.ms.ordermicroservice.domain.model.Order;
import com.ms.ordermicroservice.domain.model.OrderItem;

import com.ms.ordermicroservice.domain.model.User;
import com.ms.ordermicroservice.domain.serviceports.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
private final UserService userService;

    public OrderMapper(UserService userService) {
        this.userService = userService;
    }

    public Order toModel(OrderRequestDTO dto) {
        Order order = new Order();
        User user =  userService.findById(dto.userId());
        order.setUser(user);
        order.setTotalAmount(dto.totalAmount());
        order.setStatus(dto.status());
        List<OrderItem> items = getOrderItems(dto);
        order.setItems(items);

        return order;
    }

    private static List<OrderItem> getOrderItems(OrderRequestDTO dto) {
        return dto.items().stream()
                .map(itemDto -> new OrderItemMapper().toModel(itemDto))
                .collect(Collectors.toList());

    }


    public OrderResponseDTO toResponseDTO(Order order) {
        return new OrderResponseDTO(
                order.getId(),
                order.getUser().getId(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getItems().stream()
                        .map(item -> new OrderItemMapper().toResponseDTO(item))
                        .collect(Collectors.toList()),
                order.getCreatedAt()
        );
    }
}
