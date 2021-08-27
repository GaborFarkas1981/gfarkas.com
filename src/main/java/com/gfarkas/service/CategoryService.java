package com.gfarkas.service;

import com.gfarkas.dao.CategoryEntity;
import com.gfarkas.dto.CategoryDto;
import com.gfarkas.mapper.CategoryMapper;
import com.gfarkas.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryService {

    @Autowired
    CategoryMapper mapper;

    @Autowired
    CategoryRepository repository;

    public CategoryDto create(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = mapper.toCategoryEntity(categoryDto);
        repository.save(categoryEntity);

        return categoryDto;
    }

    public CategoryDto get(Long categoryId) {
        CategoryEntity categoryEntity = null;
        Optional<CategoryEntity> optionalCategoryEntity = repository.findById(categoryId);
        if (optionalCategoryEntity.isPresent()) {
            categoryEntity = optionalCategoryEntity.get();
        }

        return mapper.toCategoryDto(categoryEntity);
    }

    public CategoryDto getByName(String name) {
        CategoryEntity category = repository.getCategoryEntityByName(name);

        return mapper.toCategoryDto(category);
    }

    public Set<CategoryDto> list() {
        Iterable<CategoryEntity> categoryEntityIterable = repository.findAll();
        Set<CategoryEntity> categoryEntities = new HashSet<>();
        for (CategoryEntity categoryEntity : categoryEntityIterable) {
            categoryEntities.add(categoryEntity);
        }
        return mapper.toCategoryDto(categoryEntities);
    }
}
