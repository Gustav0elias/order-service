package com.ms.ordermicroservice.application.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record UserResponseDTO (UUID id, String name, String email, LocalDate createdAt) {
}
