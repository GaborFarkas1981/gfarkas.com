package com.gfarkas.repository;

import com.gfarkas.dao.Category;
import com.gfarkas.dao.Product;
import com.gfarkas.dto.CategoryDto;
import com.gfarkas.dto.ProductDto;
import com.gfarkas.mapper.CategoryMapper;
import com.gfarkas.mapper.ProductMapper;
import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.EmbeddedStorageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MediaMarktRepositoryImpl implements MediaMarktRepository {

    private final List<Category> categories;
    private final EmbeddedStorageManager categoryStorage;

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    public MediaMarktRepositoryImpl(@Value("${microstream.store.location.category}") final String location) {
        super();

        this.categories = new ArrayList<>();

        this.categoryStorage = EmbeddedStorage.start(
                this.categories,
                Paths.get(location)
        );
    }

    // category
    @Override
    public void storeAll() {
        this.categoryStorage.store(this.categories);
    }

    @Override
    public void deleteCategoryByName(String name) {
        categories.removeIf(category -> category.getName().equals(name));
    }

    @Override
    public void add(CategoryDto categoryDto) {
        this.categories.add(categoryMapper.toCategory(categoryDto));
        this.storeAll();
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        boolean oldCategoryRemoved = categories.removeIf(c -> c.getName().equals(categoryDto.getName()));
        if (oldCategoryRemoved) {
            categories.add(categoryMapper.toCategory(categoryDto));
        }
        this.storeAll();
    }

    @Override
    public List<Category> findAll() {
        return this.categories;
    }

    @Override
    public void deleteAll() {
        this.categories.clear();
        this.storeAll();
    }

    @Override
    public List<Category> findCategoryByName(final String name) {
        return this.categories.stream()
                .filter(c -> c.getName().equals(name))
                .collect(Collectors.toList());
    }

    // product
    @Override
    public List<Product> findAllProductInCategory(String categoryName) {
        List<Category> categories = this.categories.stream()
                .filter(category -> category.getName().equals(categoryName))
                .collect(Collectors.toList());

        return categories.get(0).getProducts();
    }

    @Override
    public List<Product> findProductByBrand(String brand) {
        return getProducts().stream()
                .filter(product -> product.getBrand().equals(brand))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findProductByDescription(String description) {
        return getProducts().stream()
                .filter(product -> product.getDescription().equals(description))
                .collect(Collectors.toList());
    }

    @Override
    public void addProductToCategory(ProductDto productDto) {
        for (Category c : this.categories) {
            if (c.getName().equals(productDto.getCategoryName())) {
                if (c.getProducts() == null) c.setProducts(new ArrayList<>());
                c.getProducts().add(productMapper.toProduct(productDto));
            }
        }
        this.storeAll();
    }

    @Override
    public void updateProductInCategory(ProductDto productDto) {
        for (Category c : this.categories) {
            if (c.getName().equals(productDto.getCategoryName())) {
                boolean oldProductRemoved = c.getProducts().removeIf(product -> product.getDescription().equals(productDto.getDescription()));
                if (oldProductRemoved) {
                    c.getProducts().add(productMapper.toProduct(productDto));
                }
            }
        }
        this.storeAll();
    }

    @Override
    public void deleteProductInCategory(ProductDto productDto) {
        for (Category c : this.categories) {
            if (c.getName().equals(productDto.getCategoryName())) {
                c.getProducts().removeIf(product -> product.getDescription().equals(productDto.getDescription()));
            }
        }
        this.storeAll();
    }

    @Override
    public void deleteAllProductInCategory(String categoryName) {
        for (Category c : this.categories) {
            if (c.getName().equals(categoryName)) {
                c.getProducts().clear();
            }
        }
        this.storeAll();
    }

    private List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        for (Category category : this.categories) {
            products.addAll(category.getProducts());
        }
        return products;
    }
}
