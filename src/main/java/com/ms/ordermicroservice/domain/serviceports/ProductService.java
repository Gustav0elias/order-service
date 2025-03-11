package com.ms.ordermicroservice.domain.serviceports;

import com.ms.ordermicroservice.domain.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    public Product createProduct(Product product);
    public Optional<Product> getProductById(UUID id);
    public Page<Product> getAllProducts(Pageable pageable);
    public Optional<Product> updateProduct(UUID id, Product product);
    public boolean deleteProduct(UUID id);
}
