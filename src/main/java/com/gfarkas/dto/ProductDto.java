package com.gfarkas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private Integer price;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("os")
    private String os;
}
