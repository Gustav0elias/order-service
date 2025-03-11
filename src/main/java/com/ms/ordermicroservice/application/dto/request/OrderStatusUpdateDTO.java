package com.ms.ordermicroservice.application.dto.request;

import com.ms.ordermicroservice.application.enums.StatusOrder;

import jakarta.validation.constraints.NotNull;

public record OrderStatusUpdateDTO (
    @NotNull(message = "Forneça um status válido") StatusOrder status) {
}