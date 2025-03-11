package com.ms.ordermicroservice.infrastructure.web.controller;

import com.ms.ordermicroservice.application.dto.request.ProductRequestDTO;
import com.ms.ordermicroservice.application.dto.response.PaginatedResponseDTO;
import com.ms.ordermicroservice.application.dto.response.ProductResponseDTO;
import com.ms.ordermicroservice.application.mapper.ProductMapper;
import com.ms.ordermicroservice.domain.model.Product;
import com.ms.ordermicroservice.domain.serviceports.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;


@RestController
@RequestMapping("orderms/product")
@Tag(name="Produtos", description = "API para gerencimaneto de produtos")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    @Operation(summary = "Criar novo produto", description = "Endoint para a criação de um novo produto")
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Validated ProductRequestDTO productRequestDTO) {

        Product product = productMapper.toModel(productRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.toResponseDTO(productService.createProduct(product)));
    }

    @GetMapping
    @Operation(summary = "Busca de produtos", description = "Retorna todos os podutos")
    public ResponseEntity<PaginatedResponseDTO<ProductResponseDTO>> getAllProducts( 
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
                
        Page<Product> products = productService.getAllProducts(PageRequest.of(page, size));
        Page<ProductResponseDTO>  productsResponse =  products.map(productMapper::toResponseDTO);
        return ResponseEntity.ok(new PaginatedResponseDTO<>(productsResponse));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca de produtos pelo ID", description = "Retorna todos os podutos PELO ID")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable UUID id) {
        return productService.getProductById(id).map(p->ResponseEntity
                .status(HttpStatus.OK).body(productMapper.toResponseDTO(p)))
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto", description = "Endpoint para atualizar um produto existente")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable UUID id, @RequestBody @Validated ProductRequestDTO productRequestDTO) {
        Product product = productMapper.toModel(productRequestDTO);
        return productService.updateProduct(id, product)
                .map(p -> ResponseEntity.ok(productMapper.toResponseDTO(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir produto", description = "Endpoint para excluir um produto pelo ID")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        if (productService.deleteProduct(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
