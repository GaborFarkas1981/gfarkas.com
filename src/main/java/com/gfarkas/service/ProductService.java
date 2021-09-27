package com.gfarkas.service;

import com.gfarkas.dao.Category;
import com.gfarkas.dao.Product;
import com.gfarkas.dto.ProductDto;
import com.gfarkas.mapper.ProductMapper;
import com.gfarkas.repository.MediaMarktRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductMapper mapper;

    @Autowired
    MediaMarktRepository repository;

    public void create(ProductDto productDto) {
        repository.addProductToCategory(productDto);
    }

    public List<ProductDto> getByCategoryName(String categoryName) {
        List<Product> products = repository.findAllProductInCategory(categoryName);

        return mapper.toProductDto(products);
    }

    public List<ProductDto> getByDescription(String description) {
        List<Product> products = repository.findProductByDescription(description);

        return mapper.toProductDto(products);
    }

    public List<ProductDto> getByBrand(String brand) {
        List<Product> products = repository.findProductByBrand(brand);

        return mapper.toProductDto(products);
    }

    public List<ProductDto> list(String categoryName) {
        Iterable<Product> products = repository.findAllProductInCategory(categoryName);

        return mapper.toProductDto(products);
    }

}
