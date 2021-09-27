package com.gfarkas.service;

import com.gfarkas.dto.CategoryDto;
import com.gfarkas.dto.MediaMarktDto;
import com.gfarkas.mapper.CategoryMapper;
import com.gfarkas.mapper.ProductMapper;
import com.gfarkas.repository.MediaMarktRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MassUploadService {

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    ProductMapper productMapper;

    @Autowired
    MediaMarktRepository repository;

    public void create(MediaMarktDto mediaMarktDto) {
        for (CategoryDto categoryDto : mediaMarktDto.getCategoryDtos()) {
            repository.add(categoryDto);
        }
    }
}
