package com.gfarkas;

import com.gfarkas.dto.ProductDto;
import com.gfarkas.repository.ProductRepository;
import com.gfarkas.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;
import java.util.Set;

@SpringBootTest
public class ProductServiceSpringTests extends CommonTestService{

    @Autowired
    ProductService service;

    @Autowired
    ProductRepository repository;

    @BeforeEach
    public void clearAllProduct() {
        repository.deleteAll();
    }

    @Test
    public void createProductTest() {
        ProductDto savedProduct = createProductDto(null, "Dell", 123, "description", "os", 12);
        Assertions.assertNotNull(savedProduct);
        Assertions.assertEquals(123, savedProduct.getPrice());
    }

    @Test
    public void getProductsByBrandTest() {
        createProductDto(null, "Dell", 123, "description", "os", 12);
        Set<ProductDto> receivedProducts = service.getByBrand("Dell");
        Assertions.assertNotNull(receivedProducts);
        for (Object o : receivedProducts.toArray()) {
            ProductDto productDto = (ProductDto) o;
            Assertions.assertEquals(12, productDto.getSize());
        }
    }

    @Test
    public void getProductsByDescriptionTest() {
        createProductDto(null, "Dell", 123, "description", "os", 12);
        Set<ProductDto> receivedProducts = service.getByDescription("description");
        Assertions.assertNotNull(receivedProducts);
        for (Object o : receivedProducts.toArray()) {
            ProductDto productDto = (ProductDto) o;
            Assertions.assertEquals(123, productDto.getPrice());
        }
    }

    @Test
    public void getProductsByCategoryIdTest() {
        createProductDto(null, "Dell", 123, "description", "os", 12);
        Set<ProductDto> receivedProducts = service.getProductsByCategoryId(1L);
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
            createProductDto(random, null, null, null, null, null);
        }
        Set<ProductDto> receivedProducts = service.list();
        Assertions.assertNotNull(receivedProducts);
        Assertions.assertEquals(10, receivedProducts.size());
    }

}
