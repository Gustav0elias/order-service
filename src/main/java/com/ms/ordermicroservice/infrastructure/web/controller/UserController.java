package com.ms.ordermicroservice.infrastructure.web.controller;

import com.ms.ordermicroservice.application.dto.request.UserRequestDTO;
import com.ms.ordermicroservice.application.dto.response.OrderResponseDTO;
import com.ms.ordermicroservice.application.dto.response.PaginatedResponseDTO;
import com.ms.ordermicroservice.application.dto.response.UserResponseDTO;
import com.ms.ordermicroservice.application.mapper.OrderMapper;
import com.ms.ordermicroservice.application.mapper.UserMapper;
import com.ms.ordermicroservice.domain.model.Order;
import com.ms.ordermicroservice.domain.model.User;
import com.ms.ordermicroservice.domain.serviceports.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@RestController
@RequestMapping("orderms/user")
@Tag(name="Usuários", description = "API para gerencimaneto de usuários")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final OrderMapper orderMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService, UserMapper userMapper, OrderMapper orderMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    @Operation(summary = "Criar novo usuário", description = "Endoint para a criação de um novo usuário")
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Validated UserRequestDTO userRequestDTO) {
        logger.info("Criando novo usuário: {}", userRequestDTO);
        User user = userMapper.toModel(userRequestDTO);
        UserResponseDTO response = userMapper.toResponseDTO(userService.createUser(user));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}/orders")
    @Cacheable(value = "userOrders", key = "#id")
    @Operation(summary = "Busca pedidos do usuário", description = "Retorna todos os pedidos de um usuário de acordo com aquele ID")
    public ResponseEntity<PaginatedResponseDTO<OrderResponseDTO>> getOrders(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        logger.info("Buscando pedidos para o usuário: {}", id);
        Page<Order> orders = userService.findOrdersByUser(id, PageRequest.of(page, size));
        Page<OrderResponseDTO> ordersResponse = orders.map(orderMapper::toResponseDTO);
        return ResponseEntity.ok(new PaginatedResponseDTO<>(ordersResponse));
    }
}
