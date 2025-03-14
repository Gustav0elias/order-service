package com.ms.ordermicroservice.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductRequestDTO(@NotBlank(message = "O campo de  nome não pode ser vazio") String name,
                                @NotBlank(message = "O campo de  descrição não pode ser vazio")String description,
                                @Positive(message = "O valor total deve ser positivo") BigDecimal price) {
}
