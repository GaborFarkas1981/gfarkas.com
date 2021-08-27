package com.gfarkas;

import com.gfarkas.dao.CategoryEntity;
import com.gfarkas.dao.ProductEntity;
import com.gfarkas.dto.CategoryDto;
import com.gfarkas.dto.MassUploadDto;
import com.gfarkas.dto.ProductDto;
import com.gfarkas.repository.CategoryRepository;
import com.gfarkas.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.stream.StreamSupport;

@SpringBootTest
public class MassUploadServiceSpringTest extends CommonTestService {

    @Autowired
    private MassUploadService massUploadService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void clearAll() {
        productRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    public void listProducts() {
        Random rand = new Random();
        Set<ProductDto> productDtos1 = new HashSet<>();
        Set<ProductDto> productDtos2 = new HashSet<>();
        addTenProducts(rand, productDtos1, productDtos2);

        CategoryDto category1 = new CategoryDto();
        addProductsToCategory(rand, productDtos1, category1);

        CategoryDto category2 = new CategoryDto();
        addProductsToCategory(rand, productDtos2, category2);

        createMassUploadDto(category1, category2);

        Iterable<ProductEntity> allProducts = productRepository.findAll();
        Assertions.assertNotNull(allProducts);
        Assertions.assertEquals(10, StreamSupport.stream(allProducts.spliterator(), false).count());

        Iterable<CategoryEntity> allCategories = categoryRepository.findAll();
        Assertions.assertNotNull(allCategories);
        Assertions.assertEquals(2, StreamSupport.stream(allCategories.spliterator(), false).count());
    }

    private void createMassUploadDto(CategoryDto category1, CategoryDto category2) {
        MassUploadDto massUploadDto = new MassUploadDto();
        massUploadDto.setCategoryDtos(new HashSet<>());
        massUploadDto.getCategoryDtos().add(category1);
        massUploadDto.getCategoryDtos().add(category2);
        massUploadService.create(massUploadDto);
    }

    private void addProductsToCategory(Random rand, Set<ProductDto> productDtos1, CategoryDto category) {
        category.setName(UUID.randomUUID().toString());
        category.setProductDtos(productDtos1);
    }

    private void addTenProducts(Random rand, Set<ProductDto> productDtos1, Set<ProductDto> productDtos2) {
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            ProductDto product = createProductDto(rnd, null, null, null, null, null);
            if (i < 5) {
                productDtos1.add(product);
            } else {
                productDtos2.add(product);
            }
        }
    }
}
