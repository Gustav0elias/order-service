package com.ms.ordermicroservice.infrastructure.web.controller;

import com.ms.ordermicroservice.application.dto.request.ProductRequestDTO;
import com.ms.ordermicroservice.application.dto.response.ProductResponseDTO;
import com.ms.ordermicroservice.application.mapper.ProductMapper;
import com.ms.ordermicroservice.domain.model.Product;
import com.ms.ordermicroservice.domain.serviceports.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("orderms/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Validated ProductRequestDTO productRequestDTO) {

        Product product = productMapper.toModel(productRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.toResponseDTO(productService.createProduct(product)));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductResponseDTO>  productsResponse =  products.stream().map(productMapper::toResponseDTO).toList();
        return ResponseEntity.status(HttpStatus.OK).body(productsResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable UUID id) {
        return productService.getProductById(id).map(p->ResponseEntity
                .status(HttpStatus.OK).body(productMapper.toResponseDTO(p)))
                .orElse(ResponseEntity.notFound().build());

    }
}
