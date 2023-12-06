package com.tads4.tads4.controllers;


import com.tads4.tads4.dto.UserDTO;
import com.tads4.tads4.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);

    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> findAll(@PathVariable Pageable pageable) {
        Page<UserDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);

    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> updated(@PathVariable Long id, @Valid @RequestBody UserDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
