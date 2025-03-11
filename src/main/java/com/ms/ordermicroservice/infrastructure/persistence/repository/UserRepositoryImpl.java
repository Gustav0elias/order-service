package com.ms.ordermicroservice.infrastructure.persistence.repository;

import com.ms.ordermicroservice.domain.model.Order;
import com.ms.ordermicroservice.domain.model.User;
import com.ms.ordermicroservice.domain.repositoryports.UserRepository;
import com.ms.ordermicroservice.infrastructure.persistence.entity.OrderEntity;
import com.ms.ordermicroservice.infrastructure.persistence.entity.UserEntity;
import com.ms.ordermicroservice.infrastructure.persistence.repositoryjpa.UserRepositoryJpa;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserRepositoryImpl implements UserRepository {
    private final UserRepositoryJpa userRepositoryJpa;
    private final ModelMapper modelMapper;

    public UserRepositoryImpl(UserRepositoryJpa userRepositoryJpa, ModelMapper modelMapper) {
        this.userRepositoryJpa = userRepositoryJpa;
        this.modelMapper = modelMapper;
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        UserEntity savedUserEntity = userRepositoryJpa.save(userEntity);
        return modelMapper.map(savedUserEntity, User.class);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        UserEntity userEntity = userRepositoryJpa.findByEmail(email);
        if (userEntity == null) {
            return Optional.empty();
        }
        return Optional.of(modelMapper.map(userEntity, User.class));
    }

    @Override
    public Optional<User> findById(UUID id) {
        Optional<UserEntity> userEntity = userRepositoryJpa.findById(id);
        return userEntity.map(u->modelMapper.map(u, User.class));
    }

    @Override
    public Page<Order> findOrdersByUser(UUID id, Pageable pageable) {
        Page<OrderEntity> orderEntities = userRepositoryJpa.findOrdersByUserId(id, pageable);
        return orderEntities.map(orderEntity -> modelMapper.map(orderEntity, Order.class));
    }
}
