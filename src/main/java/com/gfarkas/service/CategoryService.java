package com.gfarkas.service;

import com.gfarkas.dao.Category;
import com.gfarkas.dto.CategoryDto;
import com.gfarkas.mapper.CategoryMapper;
import com.gfarkas.repository.MediaMarktRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryMapper mapper;

    @Autowired
    MediaMarktRepository repository;

    public void create(CategoryDto categoryDto) {
        repository.add(categoryDto);
    }

    public CategoryDto getByName(String name) {
        Category category = repository.findCategoryByName(name).get(0);

        return mapper.toCategoryDto(category);
    }

    public List<CategoryDto> list() {
        List<Category> categories = repository.findAll();

        return mapper.toCategoryDto(categories);
    }
}
