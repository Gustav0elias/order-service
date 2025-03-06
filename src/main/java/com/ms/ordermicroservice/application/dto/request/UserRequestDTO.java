package com.ms.ordermicroservice.application.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.UUID;
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserRequestDTO(@NotBlank(message = "O campo nome não pode ser vazio") String name,
                             @NotBlank(message = "O campo email não pode ser vazio")  String email) {
}
