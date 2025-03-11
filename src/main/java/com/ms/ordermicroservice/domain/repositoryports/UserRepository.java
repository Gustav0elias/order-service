package com.ms.ordermicroservice.domain.repositoryports;

import com.ms.ordermicroservice.domain.model.Order;
import com.ms.ordermicroservice.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    public User createUser(User user);
    public Optional<User> findUserByEmail(String email);
    public Optional<User>  findById(UUID id);
    public Page<Order> findOrdersByUser(UUID id, Pageable pageable);
}
