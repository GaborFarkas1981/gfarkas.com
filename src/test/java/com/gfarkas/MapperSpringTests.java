package com.gfarkas;

import com.gfarkas.dao.CategoryEntity;
import com.gfarkas.dao.ProductEntity;
import com.gfarkas.dto.CategoryDto;
import com.gfarkas.dto.ProductDto;
import com.gfarkas.mapper.CategoryMapper;
import com.gfarkas.mapper.ProductMapper;
import com.gfarkas.repository.CategoryRepository;
import com.gfarkas.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
class MapperSpringTests {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void productMapperTest() {
        ProductDto productDto = createProductDto(null, "Dell", 123, "MacOs Lattitude notebook 17\"", "MacOs", 17);

        ProductEntity productEntity = productRepository.save(productMapper.toProductEntity(productDto));

        Assertions.assertNotNull(productEntity);
        Assertions.assertEquals(productDto.getDescription(), productEntity.getDescription());

        productDto = productMapper.toProductDto(productEntity);

        Assertions.assertEquals(productDto.getBrand(), productEntity.getBrand());
    }

    @Test
    public void categoryMapperTest() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setProductDtos(new HashSet<>());
        categoryDto.setName("Notebook");
        for (int i = 0; i < 10; i++) {
            categoryDto.getProductDtos().add(
                    createProductDto(new Random(), null, null, null, null, null)
            );
        }

        CategoryEntity categoryEntity = categoryRepository.save(categoryMapper.toCategoryEntity(categoryDto));

        Assertions.assertNotNull(categoryEntity);
        Assertions.assertEquals(10, categoryEntity.getProductEntities().size());
    }

    private ProductDto createProductDto(Random random, String brand, Integer price, String description, String os, Integer size) {
        if (random == null) {
            random = new Random();
        }

        ProductDto productDto = new ProductDto();

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
