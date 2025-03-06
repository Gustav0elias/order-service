package com.ms.ordermicroservice.application.dto.response;

import java.time.LocalDateTime;

public record ErrorResponseDTO(Integer id, String message, LocalDateTime timestamp) {
}
