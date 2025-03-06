package com.ms.ordermicroservice.application.service;

import com.ms.ordermicroservice.domain.model.Product;
import com.ms.ordermicroservice.domain.repositoryports.ProductRepository;
import com.ms.ordermicroservice.domain.serviceports.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.createProduct(product);
    }

    @Override
    public Optional<Product> getProductById(UUID id) {
        return productRepository.getProductById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }
}
