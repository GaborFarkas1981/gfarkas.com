package com.gfarkas.service;

import com.gfarkas.dao.CategoryEntity;
import com.gfarkas.dto.CategoryDto;
import com.gfarkas.dto.MassUploadDto;
import com.gfarkas.mapper.CategoryMapper;
import com.gfarkas.mapper.ProductMapper;
import com.gfarkas.repository.CategoryRepository;
import com.gfarkas.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MassUploadService {

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;

    public void create(MassUploadDto massUploadDto) {
        for (CategoryDto categoryDto : massUploadDto.getCategoryDtos()) {
            CategoryEntity categoryEntity = categoryMapper.toCategoryEntity(categoryDto);
            categoryRepository.save(categoryEntity);
            productRepository.saveAll(categoryEntity.getProductEntities());

        }
    }
}
