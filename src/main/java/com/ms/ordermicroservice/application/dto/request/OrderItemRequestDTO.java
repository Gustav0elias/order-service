package com.ms.ordermicroservice.application.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemRequestDTO(
        @NotNull(message = "O ID do produto não pode ser nulo") UUID productId,
        @NotNull(message = "A quantidade não pode ser nula")
        @Positive(message = "A quantidade deve ser positiva") Integer quantity,
        @NotNull(message = "O preço não pode ser nulo")
        @Positive(message = "O preço deve ser positivo") BigDecimal price
) {
}
