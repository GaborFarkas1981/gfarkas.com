package com.gfarkas.dao;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class Product {

    @Size(max = 1024)
    private String categoryName;

    @Size(max = 1024)
    private String description;

    private Integer price;

    @Size(max = 1024)
    private String brand;

    private Integer size;

    @Size(max = 1024)
    private String os;

}
