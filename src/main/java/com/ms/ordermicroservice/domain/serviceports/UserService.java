package com.ms.ordermicroservice.domain.serviceports;

import com.ms.ordermicroservice.domain.model.Order;
import com.ms.ordermicroservice.domain.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    public User createUser(User user);
    public User findById(UUID id);
    public List<Order> findOrdersByUser(UUID id);

}
