package com.ms.ordermicroservice.config;

import com.ms.ordermicroservice.application.dto.response.ErrorResponseDTO;
import com.ms.ordermicroservice.infrastructure.web.exception.BusinessException;
import com.ms.ordermicroservice.infrastructure.web.exception.ResourceNotFoundException;
import com.ms.ordermicroservice.infrastructure.web.exception.ValidationException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(ResourceNotFoundException e) {
        logger.error("Não foi possível encontrar o recurso: {}",e.getMessage());
        return new ResponseEntity<>(new ErrorResponseDTO(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationException(ValidationException e) {
        logger.error("Erro de validação: {}",e.getMessage());
        return new ResponseEntity<>(new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDTO> handleBusinessException(BusinessException e) {
        logger.error("Erro de negócio: {}",e.getMessage());
        return new ResponseEntity<>(new ErrorResponseDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(), LocalDateTime.now()), HttpStatus.UNPROCESSABLE_ENTITY);

    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));
                 logger.error("Erro de validação de entrada: {}", errorMessage);

        return new ResponseEntity<>(new ErrorResponseDTO(HttpStatus.BAD_REQUEST.value(), e.getMessage(), LocalDateTime.now()), HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception e) {
        logger.error("Erro interno: {}", e.getMessage(), e);
        return new ResponseEntity<>(new ErrorResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(), LocalDateTime.now()), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
