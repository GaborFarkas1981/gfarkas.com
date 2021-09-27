package com.gfarkas;

import com.gfarkas.dao.Product;
import com.gfarkas.dto.CategoryDto;
import com.gfarkas.dto.ProductDto;
import com.gfarkas.mapper.ProductMapper;
import com.gfarkas.repository.MediaMarktRepository;
import com.gfarkas.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class ProductServiceSpringTests extends CommonTestService {

    @Autowired
    ProductService service;

    @Autowired
    MediaMarktRepository repository;

    @Autowired
    ProductMapper mapper;

    @BeforeEach
    public void clearAll() {
        repository.deleteAll();
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Notebook");
        repository.add(categoryDto);
    }

    @Test
    public void createProductTest() {
        ProductDto savedProduct = saveProduct();
        Assertions.assertNotNull(savedProduct);
        Assertions.assertEquals(123, savedProduct.getPrice());
    }

    @Test
    public void getProductsByBrandTest() {
        saveProduct();
        List<ProductDto> receivedProducts = service.getByBrand("Dell");
        Assertions.assertNotNull(receivedProducts);
        for (Object o : receivedProducts.toArray()) {
            ProductDto productDto = (ProductDto) o;
            Assertions.assertEquals(12, productDto.getSize());
        }
    }

    @Test
    public void getProductsByDescriptionTest() {
        saveProduct();
        List<ProductDto> receivedProducts = service.getByDescription("description");
        Assertions.assertNotNull(receivedProducts);
        for (Object o : receivedProducts.toArray()) {
            ProductDto productDto = (ProductDto) o;
            Assertions.assertEquals(123, productDto.getPrice());
        }
    }

    @Test
    public void getProductsByCategoryNameTest() {
        saveProduct();
        List<ProductDto> receivedProducts = service.getByCategoryName("Notebook");
        Assertions.assertNotNull(receivedProducts);
        for (Object o : receivedProducts.toArray()) {
            ProductDto productDto = (ProductDto) o;
            Assertions.assertEquals("os", productDto.getOs());
        }
    }

    @Test
    public void listProductTest() {
        Random random = new Random();
        for (long i = 0L; i < 10; i++) {
            saveProduct();
        }
        List<ProductDto> receivedProducts = service.list("Notebook");
        Assertions.assertNotNull(receivedProducts);
        Assertions.assertEquals(10, receivedProducts.size());
    }

    private ProductDto saveProduct() {
        ProductDto productDto = createProductDto(null, "Dell", 123, "description", "os", 12);
        productDto.setCategoryName("Notebook");
        repository.addProductToCategory(productDto);

        return productDto;
    }

}
