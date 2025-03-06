package com.ms.ordermicroservice.domain.repositoryports;

import com.ms.ordermicroservice.domain.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    public Product createProduct(Product product);
    public Optional<Product> getProductById(UUID id);
    public List<Product> getAllProducts();
}
