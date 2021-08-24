package com.gfarkas.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class MassUploadDto  {
    @JsonProperty("categories")
    private Set<CategoryDto> categoryDtos;
}
