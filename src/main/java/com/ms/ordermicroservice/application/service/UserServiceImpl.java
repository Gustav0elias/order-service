package com.ms.ordermicroservice.application.service;

import com.ms.ordermicroservice.domain.model.Order;
import com.ms.ordermicroservice.domain.model.User;
import com.ms.ordermicroservice.domain.repositoryports.UserRepository;
import com.ms.ordermicroservice.domain.serviceports.UserService;
import com.ms.ordermicroservice.infrastructure.persistence.entity.OrderEntity;

import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.createUser(user);
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public List<Order> findOrdersByUser(UUID id) {

        return userRepository.findOrdersByUser(id);
    }
}
