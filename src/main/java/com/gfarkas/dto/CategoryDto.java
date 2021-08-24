package com.gfarkas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
public class CategoryDto {

    @NotNull
    @JsonProperty("name")
    private String name;

    @JsonProperty("products")
    private Set<ProductDto> productDtos;

}
