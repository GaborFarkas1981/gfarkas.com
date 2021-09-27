package com.gfarkas.dao;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MediaMarkt {
    private List<Category> categories = new ArrayList<>();
}
