package com.ms.ordermicroservice.application.dto.request;

import com.ms.ordermicroservice.application.enums.StatusOrder;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record OrderRequestDTO(
        @NotNull(message = "O ID do usuário não pode ser nulo") UUID userId,
        @NotNull(message = "O status do pedido não pode ser nulo") StatusOrder status,
        @NotNull(message = "A lista de itens não pode ser nula") List<OrderItemRequestDTO> items,
        @NotNull(message = "O valor total não pode ser nulo")
        @Positive(message = "O valor total deve ser positivo") BigDecimal totalAmount
) {}
