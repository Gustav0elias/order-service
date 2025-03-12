package com.ms.ordermicroservice.infrastructure.web.controller;

import com.ms.ordermicroservice.application.dto.request.OrderItemRequestDTO;
import com.ms.ordermicroservice.application.dto.request.OrderRequestDTO;
import com.ms.ordermicroservice.application.dto.request.OrderStatusUpdateDTO;
import com.ms.ordermicroservice.application.dto.response.OrderResponseDTO;
import com.ms.ordermicroservice.application.mapper.OrderItemMapper;
import com.ms.ordermicroservice.application.mapper.OrderMapper;
import com.ms.ordermicroservice.domain.model.Order;
import com.ms.ordermicroservice.domain.serviceports.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("orderms/order")
@Tag(name="Pedidos", description = "API para gerencimaneto de pedidos")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper, OrderItemMapper orderItemMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
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

    @PatchMapping("/{id}/status")
    @Operation(summary = "Atualizar status do pedido", description = "Endpoint para atualizar o status de um pedido existente")
    public ResponseEntity<OrderResponseDTO> updateStatus (@PathVariable UUID id, @RequestBody @Validated OrderStatusUpdateDTO orderStatusUpdateDTO){
        return orderService.updateOrderStatus(id, orderStatusUpdateDTO.status())
        .map(order -> ResponseEntity.ok(orderMapper.toResponseDTO(order)))
        .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/items")
    @Operation(summary = "Adicionar item ao pedido", description = "Endpoint para adicionar um item ao pedido")
    public ResponseEntity<OrderResponseDTO> addItem(@PathVariable UUID id, @RequestBody @Validated OrderItemRequestDTO orderItemRequestDTO){
        Order updateOrder = orderService.addItemToOrder(id, orderItemMapper.toModel(orderItemRequestDTO));
        return ResponseEntity.ok(orderMapper.toResponseDTO(updateOrder));
    }

    @DeleteMapping("/{orderId}/items/{itemId}")
    @Operation(summary = "Remover item do pedido", description = "Endpoint para remover um item de um pedido existente")
    public ResponseEntity<OrderResponseDTO> removeItemFromOrder( @PathVariable UUID orderId, @PathVariable UUID itemId) {
        Order updatedOrder = orderService.removeItemFromOrder(orderId, itemId);
        return ResponseEntity.ok(orderMapper.toResponseDTO(updatedOrder));
}
    

}
