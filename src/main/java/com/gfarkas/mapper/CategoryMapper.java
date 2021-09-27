package com.gfarkas.mapper;

import com.gfarkas.dao.Category;
import com.gfarkas.dao.Product;
import com.gfarkas.dto.CategoryDto;
import com.gfarkas.dto.ProductDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public abstract class CategoryMapper {

    public abstract CategoryDto toCategoryDto(Category category);

    public abstract List<CategoryDto> toCategoryDto(Iterable<Category> categoryEntities);

    public abstract Category toCategory(CategoryDto categoryDto);

    public abstract List<ProductDto> toProductDto(List<Product> productEntities);

    public abstract List<Product> toProduct(List<ProductDto> productDtos);

    @AfterMapping
    public void toCategory(CategoryDto categoryDto, @MappingTarget Category category) {
        List<ProductDto> productDtos = categoryDto.getProductDtos();
        List<Product> products = toProduct(productDtos);

        if (products != null) {
            for (Product product : products) {
                product.setCategoryName(category.getName());
            }
        }


        category.setProducts(products);
    }
}
