package com.ms.ordermicroservice.application.mapper;

import com.ms.ordermicroservice.application.dto.request.UserRequestDTO;
import com.ms.ordermicroservice.application.dto.response.UserResponseDTO;
import com.ms.ordermicroservice.domain.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toModel(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        return user;
    }

    public UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt());
    }
}
