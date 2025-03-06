package com.ms.ordermicroservice.application.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponseDTO (UUID id, String name, String description, BigDecimal price, LocalDateTime createdAt ) {
}
