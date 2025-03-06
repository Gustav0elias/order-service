package com.ms.ordermicroservice.application.dto.response;

import com.ms.ordermicroservice.application.enums.StatusOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderResponseDTO(
        UUID id,
        UUID userId,
        BigDecimal totalAmount,
        StatusOrder status,
        List<OrderItemResponseDTO> items,
        LocalDateTime createdAt
) {}