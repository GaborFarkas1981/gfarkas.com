package com.gfarkas;

import com.gfarkas.dto.ProductDto;
import com.gfarkas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
class CommonTestService {

    @Autowired
    ProductService service;

    protected ProductDto createProductDto(Random random, String brand, Integer price, String description, String os, Integer size) {
        if (random == null) {
            random = new Random();
        }

        ProductDto productDto = new ProductDto();
        productDto.setCategoryId(1L);

        if (brand == null) {
            brand = UUID.randomUUID().toString();
        }
        productDto.setBrand(brand);

        if (size == null) {
            size = 1 + random.nextInt((10000 - 1) + 1);
        }
        productDto.setSize(size);

        if (price == null) {
            price = 1 + random.nextInt((10000 - 1) + 1);
        }
        productDto.setPrice(price);

        if (description == null) {
            description = UUID.randomUUID().toString();
        }
        productDto.setDescription(description);
        if (os == null) {
            os = UUID.randomUUID().toString();
        }
        productDto.setOs(os);

        return productDto;
    }
}
