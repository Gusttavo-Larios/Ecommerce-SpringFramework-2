package com.tads4.tads4.controllers;


import com.tads4.tads4.dto.OrderDTO;
import com.tads4.tads4.dto.OrderFormDTO;
import com.tads4.tads4.dto.PaymentFormDTO;
import com.tads4.tads4.dto.UserDTO;
import com.tads4.tads4.service.OrderService;
import com.tads4.tads4.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/")
public class OrderController {

    @Autowired
    private OrderService service;


    @PostMapping(value = "/orders")
    public ResponseEntity<OrderDTO> insert(@Valid @RequestBody OrderFormDTO dto) {
        OrderDTO response = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = "/orders/{id}/payment")
    @Transactional
    public ResponseEntity<OrderDTO> insert(@PathVariable Long id) {
        OrderDTO response = service.payment(id);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}
