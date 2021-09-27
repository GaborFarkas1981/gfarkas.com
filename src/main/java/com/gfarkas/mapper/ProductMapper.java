package com.gfarkas.mapper;

import com.gfarkas.dao.Product;
import com.gfarkas.dto.ProductDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface ProductMapper {

    ProductDto toProductDto(Product product);

    List<ProductDto> toProductDto(Iterable<Product> productEntities);

    Product toProduct(ProductDto productDto);

}
