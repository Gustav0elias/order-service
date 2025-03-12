package com.ms.ordermicroservice.application.dto.response;

import java.util.UUID;

import com.ms.ordermicroservice.application.enums.StatusOrder;

public record OrderStatusChangeEventDTO(UUID orderId, StatusOrder status, String email) {}
