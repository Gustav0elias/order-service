package com.ms.ordermicroservice.domain.repositoryports;

import com.ms.ordermicroservice.domain.model.Order;
import com.ms.ordermicroservice.domain.model.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository {
    public User createUser(User user);
    public User findUserByEmail(String email);
    public User findById(UUID id);
    public List<Order> findOrdersByUser(UUID id);
}
