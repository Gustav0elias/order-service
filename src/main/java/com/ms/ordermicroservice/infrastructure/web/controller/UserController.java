package com.ms.ordermicroservice.infrastructure.web.controller;

import com.ms.ordermicroservice.application.dto.request.UserRequestDTO;
import com.ms.ordermicroservice.application.dto.response.OrderResponseDTO;
import com.ms.ordermicroservice.application.dto.response.UserResponseDTO;
import com.ms.ordermicroservice.application.mapper.OrderMapper;
import com.ms.ordermicroservice.application.mapper.UserMapper;
import com.ms.ordermicroservice.domain.model.Order;
import com.ms.ordermicroservice.domain.model.User;
import com.ms.ordermicroservice.domain.serviceports.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("orderms/user")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final OrderMapper orderMapper;
    public UserController(UserService userService, UserMapper userMapper, OrderMapper orderMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO userRequestDTO) {
        User user =userMapper.toModel(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toResponseDTO(userService.createUser(user)));
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<List<OrderResponseDTO>> getOrders(@PathVariable UUID id) {
        List<Order> orders = userService.findOrdersByUser(id);
        List<OrderResponseDTO> ordersResponse = orders.stream().map(orderMapper::toResponseDTO).toList();
        return ResponseEntity.status(HttpStatus.OK).body(ordersResponse);
    }

}
