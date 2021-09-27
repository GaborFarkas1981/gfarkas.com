package com.gfarkas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class CategoryDto {

    @NotNull
    @JsonProperty("name")
    private String name;

    @JsonProperty("products")
    private List<ProductDto> productDtos;

}
