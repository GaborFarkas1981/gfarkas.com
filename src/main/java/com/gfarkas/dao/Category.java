package com.gfarkas.dao;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class Category {

    private List<Product> products = new ArrayList<>();
    @Size(max = 1024)
    private String name;
}
