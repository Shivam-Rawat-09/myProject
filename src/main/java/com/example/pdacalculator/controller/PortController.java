package com.example.pdacalculator.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pdacalculator.model.Port;
import com.example.pdacalculator.repository.PortRepository;

@RestController
@RequestMapping("/api/ports")
public class PortController {
    private final PortRepository portRepository;

    public PortController(PortRepository portRepository) {
        this.portRepository = portRepository;
    }

    @GetMapping
    public ResponseEntity<List<Port>> getAllPorts() {
        List<Port> ports = portRepository.findAll();
        return ResponseEntity.ok(ports);
    }
} 