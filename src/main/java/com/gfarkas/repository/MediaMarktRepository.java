package com.gfarkas.repository;

import com.gfarkas.dao.Category;
import com.gfarkas.dao.Product;
import com.gfarkas.dto.CategoryDto;
import com.gfarkas.dto.ProductDto;

import java.util.List;

public interface MediaMarktRepository {
    void add(CategoryDto categoryDto);
    void updateCategory(CategoryDto categoryDto);
    List<Category> findAll();
    List<Category> findCategoryByName(String name);
    void deleteAll();
    void storeAll();
    void deleteCategoryByName(String name);

    // products
    List<Product> findAllProductInCategory(String categoryName);
    List<Product> findProductByBrand(String brand);
    List<Product> findProductByDescription(String description);
    void addProductToCategory(ProductDto product);
    void updateProductInCategory(ProductDto product);
    void deleteProductInCategory(ProductDto product);
    void deleteAllProductInCategory(String categoryName);
}
