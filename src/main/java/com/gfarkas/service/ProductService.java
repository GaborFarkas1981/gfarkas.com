package com.gfarkas.service;

import com.gfarkas.dao.ProductEntity;
import com.gfarkas.dto.ProductDto;
import com.gfarkas.mapper.ProductMapper;
import com.gfarkas.repository.CategoryRepository;
import com.gfarkas.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductService {

    @Autowired
    ProductMapper mapper;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public ProductDto create(ProductDto productDto) {
        ProductEntity productEntity = mapper.toProductEntity(productDto);
        ProductEntity savedEntity = productRepository.save(productEntity);

        return mapper.toProductDto(savedEntity);
    }

    public Set<ProductDto> getByDescription(String description) {
        Set<ProductEntity> productEntities = productRepository.findByDescription(description);

        return mapper.toProductDto(productEntities);
    }

    public Set<ProductDto> getByBrand(String brand) {
        Set<ProductEntity> productEntities = productRepository.findByBrand(brand);

        return mapper.toProductDto(productEntities);
    }

    public Set<ProductDto> list() {
        Iterable<ProductEntity> productEntities = productRepository.findAll();

        return mapper.toProductDto(productEntities);
    }

    public Set<ProductDto> getProductsByCategoryId(Long categoryId) {
        Set<ProductEntity> productEntities = productRepository.findByCategoryEntity_Id(categoryId);

        return mapper.toProductDto(productEntities);
    }
}
