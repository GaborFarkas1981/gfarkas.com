package com.gfarkas.mapper;

import com.gfarkas.dao.CategoryEntity;
import com.gfarkas.dao.ProductEntity;
import com.gfarkas.dto.CategoryDto;
import com.gfarkas.dto.ProductDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.Set;

@Mapper(
        componentModel = "spring"
)
public abstract class CategoryMapper {

    public abstract CategoryDto toCategoryDto(CategoryEntity categoryEntity);

    public abstract Set<CategoryDto> toCategoryDto(Iterable<CategoryEntity> categoryEntities);

    public abstract CategoryEntity toCategoryEntity(CategoryDto categoryDto);

    public abstract Set<ProductDto> toProductDto(Set<ProductEntity> productEntities);

    public abstract Set<ProductEntity> toProductEntity(Set<ProductDto> productDtos);

    @AfterMapping
    public void toCategoryDto(CategoryEntity categoryEntity, @MappingTarget CategoryDto categoryDto) {
        categoryDto.setProductDtos(toProductDto(categoryEntity.getProductEntities()));
    }

    @AfterMapping
    public void toCategoryEntity(CategoryDto categoryDto, @MappingTarget CategoryEntity categoryEntity) {
        Set<ProductDto> productDtos = categoryDto.getProductDtos();
        Set<ProductEntity> productEntities = toProductEntity(productDtos);

        if (productEntities != null) {
            for (ProductEntity productEntity : productEntities) {
                productEntity.setCategoryEntity(categoryEntity);
            }
        }


        categoryEntity.setProductEntities(productEntities);
    }
}
