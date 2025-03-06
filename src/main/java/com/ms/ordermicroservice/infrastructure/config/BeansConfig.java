package com.ms.ordermicroservice.infrastructure.config;

import com.ms.ordermicroservice.application.service.OrderItemServiceImpl;
import com.ms.ordermicroservice.application.service.OrderServiceImpl;
import com.ms.ordermicroservice.application.service.ProductServiceImpl;
import com.ms.ordermicroservice.application.service.UserServiceImpl;
import com.ms.ordermicroservice.domain.repositoryports.OrderItemRepository;
import com.ms.ordermicroservice.domain.repositoryports.OrderRepository;
import com.ms.ordermicroservice.domain.repositoryports.ProductRepository;
import com.ms.ordermicroservice.domain.repositoryports.UserRepository;
import com.ms.ordermicroservice.domain.serviceports.OrderItemService;
import com.ms.ordermicroservice.domain.serviceports.OrderService;
import com.ms.ordermicroservice.domain.serviceports.ProductService;
import com.ms.ordermicroservice.domain.serviceports.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductServiceImpl(productRepository);
    }

    @Bean
    public OrderService orderService(OrderRepository orderRepository) {
        return new OrderServiceImpl(orderRepository);
    }

    @Bean
    public OrderItemService orderItemService(OrderItemRepository orderItemRepository) {
        return new OrderItemServiceImpl(orderItemRepository);
    }
}
