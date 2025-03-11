package com.ms.ordermicroservice.domain.repositoryports;

import com.ms.ordermicroservice.domain.model.Product;

 
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface ProductRepository {
    public Product createProduct(Product product);
    public Optional<Product> getProductById(UUID id);
    public Page<Product> getAllProducts(Pageable pageable);
    public Optional<Product> updateProduct(UUID id, Product product);
    public boolean deleteProduct(UUID id);
}
