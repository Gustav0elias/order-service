package com.ms.ordermicroservice.application.service;

import com.ms.ordermicroservice.domain.model.Product;
import com.ms.ordermicroservice.domain.repositoryports.ProductRepository;
import com.ms.ordermicroservice.domain.serviceports.ProductService;
import com.ms.ordermicroservice.infrastructure.web.exception.BusinessException;
import com.ms.ordermicroservice.infrastructure.web.exception.ResourceNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    
        productRepository.findProductByName(product.getName())
                .ifPresent(existingProduct -> {
                    throw new BusinessException("Já existe um produto com este nome cadastrado.");
                });
                

        return productRepository.createProduct(product);
    }

    @Override
    public Optional<Product> getProductById(UUID id) {
        return productRepository.getProductById(id);
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.getAllProducts(pageable);
    }
    @Override
    public Optional<Product> updateProduct(UUID id, Product product) {
        Product existingProduct = productRepository.getProductById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado."));
        return productRepository.updateProduct(id, existingProduct);
    }

    @Override
    public boolean deleteProduct(UUID id) {
       productRepository.getProductById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado."));
        
        return productRepository.deleteProduct(id);
    }

   
}
