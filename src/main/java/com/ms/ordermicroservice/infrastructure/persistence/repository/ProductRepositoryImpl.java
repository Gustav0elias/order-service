package com.ms.ordermicroservice.infrastructure.persistence.repository;

import com.ms.ordermicroservice.domain.model.Product;
import com.ms.ordermicroservice.domain.repositoryports.ProductRepository;
import com.ms.ordermicroservice.infrastructure.persistence.entity.ProductEntity;
import com.ms.ordermicroservice.infrastructure.persistence.repositoryjpa.ProductRepositoryJpa;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductRepositoryJpa productRepositoryJpa;
    private final ModelMapper modelMapper;

    public ProductRepositoryImpl(ProductRepositoryJpa productRepositoryJpa, ModelMapper modelMapper) {
        this.productRepositoryJpa = productRepositoryJpa;
        this.modelMapper = modelMapper;
    }

    @Override
    public Product createProduct(Product product) {
        ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);
        ProductEntity productEntitySaved = productRepositoryJpa.save(productEntity);
        return modelMapper.map(productEntitySaved, Product.class);
    }

    @Override
    public Optional<Product> getProductById(UUID id) {
        return productRepositoryJpa.findById(id).map(p->modelMapper.map(p, Product.class));
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        Page <ProductEntity> productEntities = productRepositoryJpa.findAll(pageable);
        return  productEntities.map(p->modelMapper.map(p, Product.class));
    }
  
 

}
