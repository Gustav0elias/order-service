package com.ms.ordermicroservice.application.mapper;

import com.ms.ordermicroservice.application.dto.request.ProductRequestDTO;
import com.ms.ordermicroservice.application.dto.response.ProductResponseDTO;
import com.ms.ordermicroservice.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toModel(ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        return product;
    }

    public ProductResponseDTO toResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt()
        );
    }
}