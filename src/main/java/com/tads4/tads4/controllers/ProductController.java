package com.tads4.tads4.controllers;


import com.tads4.tads4.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@REquestController
@RequestMapping(value = "/products")
public class Produtcontrollers {

    @Autowired
    private ProductService service;

    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id) {
       return  service.findById(id);

    }

}
