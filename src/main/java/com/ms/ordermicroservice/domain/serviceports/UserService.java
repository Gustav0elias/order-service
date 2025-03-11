package com.ms.ordermicroservice.domain.serviceports;

import com.ms.ordermicroservice.domain.model.Order;
import com.ms.ordermicroservice.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.UUID;

public interface UserService {
    public User createUser(User user);
    public User findById(UUID id);
    public Page<Order> findOrdersByUser(UUID id, Pageable pageable);

}
