package com.example.pdacalculator.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pdacalculator.model.Vessel;
import com.example.pdacalculator.repository.VesselRepository;

@RestController
@RequestMapping("/api/vessels")
public class VesselController {
    private final VesselRepository vesselRepository;

    public VesselController(VesselRepository vesselRepository) {
        this.vesselRepository = vesselRepository;
    }

    @GetMapping
    public ResponseEntity<List<Vessel>> getAllVessels() {
        List<Vessel> vessels = vesselRepository.findAll();
        return ResponseEntity.ok(vessels);
    }
} 