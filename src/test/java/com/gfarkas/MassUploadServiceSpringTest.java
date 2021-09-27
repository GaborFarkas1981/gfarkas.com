package com.gfarkas;

import com.gfarkas.dao.Category;
import com.gfarkas.dao.Product;
import com.gfarkas.dto.CategoryDto;
import com.gfarkas.dto.MediaMarktDto;
import com.gfarkas.dto.ProductDto;
import com.gfarkas.repository.MediaMarktRepository;
import com.gfarkas.service.MassUploadService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.StreamSupport;

import static java.util.UUID.randomUUID;

@SpringBootTest
public class MassUploadServiceSpringTest extends CommonTestService {

    @Autowired
    private MassUploadService massUploadService;

    @Autowired
    private MediaMarktRepository repository;

    @BeforeEach
    public void clearAll() {
        repository.deleteAll();
    }

    @Test
    public void listProducts() {
        Random rand = new Random();
        List<ProductDto> productDtos1 = new ArrayList<>();
        List<ProductDto> productDtos2 = new ArrayList<>();
        addTenProducts(rand, productDtos1, productDtos2);

        CategoryDto category1 = new CategoryDto();
        category1.setName("Category1");
        addProductsToCategory(productDtos1, category1);

        CategoryDto category2 = new CategoryDto();
        category1.setName("Category2");
        addProductsToCategory(productDtos2, category2);

        createMassUploadDto(category1, category2);

        List<Category> allCategories = repository.findAll();
        List<Product> allProducts = new ArrayList<>();
        for (Category category : allCategories) {
            allProducts.addAll(category.getProducts());
        }
        Assertions.assertNotNull(allProducts);
        Assertions.assertEquals(10, (long) allProducts.size());

        Assertions.assertNotNull(allCategories);
        Assertions.assertEquals(2, (long) allCategories.size());
    }

    private void createMassUploadDto(CategoryDto category1, CategoryDto category2) {
        MediaMarktDto mediaMarktDto = new MediaMarktDto();
        mediaMarktDto.setCategoryDtos(new ArrayList<>());
        mediaMarktDto.getCategoryDtos().add(category1);
        mediaMarktDto.getCategoryDtos().add(category2);
        massUploadService.create(mediaMarktDto);
    }

    private void addProductsToCategory(List<ProductDto> productDtos, CategoryDto category) {
        category.setName(Objects.requireNonNullElse(category.getName(), randomUUID().toString()));
        category.setProductDtos(productDtos);
    }

    private void addTenProducts(Random rand, List<ProductDto> productDtos1, List<ProductDto> productDtos2) {
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
