package com.gfarkas;

import com.gfarkas.dao.Product;
import com.gfarkas.dto.CategoryDto;
import com.gfarkas.dto.ProductDto;
import com.gfarkas.mapper.CategoryMapper;
import com.gfarkas.mapper.ProductMapper;
import com.gfarkas.repository.MediaMarktRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootTest
class MapperSpringTests {

    @Autowired
    private MediaMarktRepository repository;

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @BeforeEach
    public void createCategory() {
        repository.deleteAll();
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Notebook");
        repository.add(categoryDto);
    }

    @Test
    public void productMapperTest() {
        ProductDto productDto = createProductDto(null, null, "Dell", 123, "MacOs Lattitude notebook 17\"", "MacOs", 17);

        repository.addProductToCategory(productDto);

        List<Product> notebooks = repository.findAllProductInCategory("Notebook");
        Assertions.assertNotNull(notebooks);
        Assertions.assertEquals(notebooks.get(0).getDescription(), productDto.getDescription());

        productDto = productMapper.toProductDto(notebooks.get(0));

        Assertions.assertEquals(productDto.getBrand(), productDto.getBrand());
    }

    private ProductDto createProductDto(Random random, String categoryName, String brand, Integer price, String description, String os, Integer size) {
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

        if (categoryName == null) {
            categoryName = "Notebook";
        }
        productDto.setCategoryName(categoryName);

        return productDto;
    }
}
