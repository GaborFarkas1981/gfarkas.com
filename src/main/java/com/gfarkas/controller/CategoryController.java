package com.gfarkas.controller;

import com.gfarkas.dto.CategoryDto;
import com.gfarkas.service.CategoryService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping()
    public ResponseEntity<CategoryDto> create(@Valid @RequestBody @ApiParam(required = true) CategoryDto categoryDto) {
        service.create(categoryDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> get(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(service.get(id), HttpStatus.OK
        );
    }

    @GetMapping()
    public ResponseEntity<Set<CategoryDto>> list() {
        return new ResponseEntity<>(service.list(), HttpStatus.OK
        );
    }

}
