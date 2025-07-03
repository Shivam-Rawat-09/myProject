package com.example.pdacalculator.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pdacalculator.model.CargoType;
import com.example.pdacalculator.repository.CargoTypeRepository;

@RestController
@RequestMapping("/api/cargo-types")
public class CargoTypeController {
    private final CargoTypeRepository cargoTypeRepository;

    public CargoTypeController(CargoTypeRepository cargoTypeRepository) {
        this.cargoTypeRepository = cargoTypeRepository;
    }

    @GetMapping
    public ResponseEntity<List<CargoType>> getAllCargoTypes() {
        List<CargoType> cargoTypes = cargoTypeRepository.findAll();
        return ResponseEntity.ok(cargoTypes);
    }
} 