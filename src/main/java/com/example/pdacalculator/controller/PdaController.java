package com.example.pdacalculator.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pdacalculator.model.Pda;
import com.example.pdacalculator.repository.PdaRepository;
import com.example.pdacalculator.service.PdaCalculationRequest;
import com.example.pdacalculator.service.PdaCalculationService;

@RestController
@RequestMapping("/api/pdas")
public class PdaController {
    private final PdaCalculationService pdaCalculationService;
    private final PdaRepository pdaRepository;

    public PdaController(PdaCalculationService pdaCalculationService, PdaRepository pdaRepository) {
        this.pdaCalculationService = pdaCalculationService;
        this.pdaRepository = pdaRepository;
    }

    @PostMapping("/calculate")
    public ResponseEntity<Pda> calculateAndSavePda(@RequestBody PdaCalculationRequest request) {
        Pda pda = pdaCalculationService.calculateAndSavePda(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(pda);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pda> getPdaById(@PathVariable Long id) {
        Optional<Pda> pdaOpt = pdaRepository.findById(id);
        return pdaOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
} 