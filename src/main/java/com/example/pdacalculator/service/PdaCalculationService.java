package com.example.pdacalculator.service;

import com.example.pdacalculator.model.Pda;

public interface PdaCalculationService {
    Pda calculateAndSavePda(PdaCalculationRequest request);
} 