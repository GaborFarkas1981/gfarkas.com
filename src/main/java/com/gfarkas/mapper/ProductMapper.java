package com.gfarkas.mapper;

import com.gfarkas.dao.ProductEntity;
import com.gfarkas.dto.ProductDto;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(
        componentModel = "spring"
)
public interface ProductMapper {

    ProductDto toProductDto(ProductEntity productEntity);

    Set<ProductDto> toProductDto(Iterable<ProductEntity> productEntities);

    ProductEntity toProductEntity(ProductDto productDto);

}
