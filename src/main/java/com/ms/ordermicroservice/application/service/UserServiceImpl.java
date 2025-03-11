package com.ms.ordermicroservice.application.service;

import com.ms.ordermicroservice.domain.model.Order;
import com.ms.ordermicroservice.domain.model.User;
import com.ms.ordermicroservice.domain.repositoryports.UserRepository;
import com.ms.ordermicroservice.domain.serviceports.UserService;
import com.ms.ordermicroservice.infrastructure.web.exception.BusinessException;
import com.ms.ordermicroservice.infrastructure.web.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        userRepository.findUserByEmail(user.getEmail())
                .ifPresent(existingUser -> {
                    throw new BusinessException("Já existe um usuário com este e-mail cadastrado.");
                });
        return userRepository.createUser(user);
    }


    @Override
    public User findById(UUID id) {
     return userRepository.findById(id)
             .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado."));
    }

    @Override
    public Page<Order> findOrdersByUser(UUID id, Pageable pageable) {
        return userRepository.findOrdersByUser(id, pageable);
    }
}
