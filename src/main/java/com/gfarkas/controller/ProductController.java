package com.gfarkas.controller;

import com.gfarkas.dto.ProductDto;
import com.gfarkas.service.ProductService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping()
    public ResponseEntity<ProductDto> create(@Valid @RequestBody @ApiParam(required = true) ProductDto productDto) {
        service.create(productDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Set<ProductDto>> get(@PathVariable(value = "brand") String brand) {
        return new ResponseEntity<>(service.getByBrand(brand),
                HttpStatus.OK
        );
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Set<ProductDto>> list(@PathVariable("categoryId") Long categoryId) {
        return new ResponseEntity<>(service.getProductsByCategoryId(categoryId),
                HttpStatus.OK
        );
    }

    @GetMapping()
    public ResponseEntity<Set<ProductDto>> list() {
        return new ResponseEntity<>(service.list(),
                HttpStatus.OK
        );
    }

}