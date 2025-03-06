package com.ms.ordermicroservice.infrastructure.web.controller;

import com.ms.ordermicroservice.application.dto.request.OrderItemRequestDTO;
import com.ms.ordermicroservice.application.dto.request.OrderRequestDTO;
import com.ms.ordermicroservice.application.dto.response.OrderItemResponseDTO;
import com.ms.ordermicroservice.application.dto.response.OrderResponseDTO;
import com.ms.ordermicroservice.application.mapper.OrderMapper;
import com.ms.ordermicroservice.domain.model.Order;
import com.ms.ordermicroservice.domain.serviceports.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("orderms/order")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        Order order = orderMapper.toModel(orderRequestDTO);
        Order orderSaved = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderMapper.toResponseDTO(orderSaved));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<Order> orders = orderService.findAllOrders();
         List<OrderResponseDTO> orderResponse = orders.stream().map(orderMapper::toResponseDTO).toList();
         return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable UUID id) {
        return orderService.findOrderById(id).map(o->ResponseEntity
                        .status(HttpStatus.OK).body(orderMapper.toResponseDTO(o)))
                .orElse(ResponseEntity.notFound().build());
    }


}
