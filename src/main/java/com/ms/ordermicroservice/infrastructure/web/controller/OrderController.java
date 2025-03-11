package com.ms.ordermicroservice.infrastructure.web.controller;

import com.ms.ordermicroservice.application.dto.request.OrderRequestDTO;
import com.ms.ordermicroservice.application.dto.response.OrderResponseDTO;
import com.ms.ordermicroservice.application.mapper.OrderMapper;
import com.ms.ordermicroservice.domain.model.Order;
import com.ms.ordermicroservice.domain.serviceports.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("orderms/order")
@Tag(name="Pedidos", description = "API para gerencimaneto de pedidos")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    @Operation(summary = "Criar novo pedido", description = "Endoint para a criação de um novo pedido")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
        Order order = orderMapper.toModel(orderRequestDTO);
        Order orderSaved = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderMapper.toResponseDTO(orderSaved));
    }

    @GetMapping
    @Operation(summary = "Busca de pedidos", description = "Endoint que retorna todos os pedidos")
    public ResponseEntity<List<OrderResponseDTO>> getAllOrders() {
        List<Order> orders = orderService.findAllOrders();
         List<OrderResponseDTO> orderResponse = orders.stream().map(orderMapper::toResponseDTO).toList();
         return ResponseEntity.status(HttpStatus.OK).body(orderResponse);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca de pedidos por id", description = "Endoint que retorna todos os pedidos pelo id")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable UUID id) {
        return orderService.findOrderById(id).map(o->ResponseEntity
                        .status(HttpStatus.OK).body(orderMapper.toResponseDTO(o)))
                .orElse(ResponseEntity.notFound().build());
    }


}
