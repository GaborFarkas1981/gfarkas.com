package com.gfarkas.controller;

import com.gfarkas.dto.CategoryDto;
import com.gfarkas.dto.MediaMarktDto;
import com.gfarkas.service.MassUploadService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/massupload")
@CrossOrigin(origins = {"http://localhost", "http://localhost:4200"})
public class MassUploadController {

    @Autowired
    private MassUploadService service;

    @PostMapping()
    public ResponseEntity<List<CategoryDto>> create
            (@Valid @RequestBody @ApiParam(required = true) MediaMarktDto mediaMarktDto) {
        service.create(mediaMarktDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
