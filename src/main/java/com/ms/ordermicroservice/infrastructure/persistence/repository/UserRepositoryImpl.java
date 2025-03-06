package com.ms.ordermicroservice.infrastructure.persistence.repository;

import com.ms.ordermicroservice.domain.model.Order;
import com.ms.ordermicroservice.domain.model.User;
import com.ms.ordermicroservice.domain.repositoryports.UserRepository;
import com.ms.ordermicroservice.infrastructure.persistence.entity.OrderEntity;
import com.ms.ordermicroservice.infrastructure.persistence.entity.UserEntity;
import com.ms.ordermicroservice.infrastructure.persistence.repositoryjpa.UserRepositoryJpa;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public User findUserByEmail(String email) {
      UserEntity userEntity =  userRepositoryJpa.findByEmail(email);
      return modelMapper.map(userEntity, User.class);
    }

    @Override
    public User findById(UUID id) {
        return null;
    }

    @Override
    public List<Order> findOrdersByUser(UUID id) {
        List<OrderEntity> orderEntities = userRepositoryJpa.findOrdersByUserId(id);
        return orderEntities.stream().map(o->modelMapper.map(o, Order.class)).toList();
    }
}
