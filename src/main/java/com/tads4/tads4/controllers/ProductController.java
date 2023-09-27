package com.tads4.tads4.controllers;


import com.tads4.tads4.dto.ProductDTO;
import com.tads4.tads4.entities.Product;
import com.tads4.tads4.repositories.ProductRepository;
import com.tads4.tads4.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return  service.findById(id);

    }

    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable) {
        return  service.findAll(pageable);

    }

    @PostMapping
    public ProductDTO inserir (@RequestBody ProductDTO dto) {
        /*dto = service.insert(dto)
        return  dto;*/
        return service.insert(dto);
    }

}
