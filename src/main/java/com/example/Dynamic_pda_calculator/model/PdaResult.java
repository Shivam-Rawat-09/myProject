package com.example.Dynamic_pda_calculator.model;

import java.util.Map;

import lombok.Data;

@Data
public class PdaResult {
    private PdaForm input;
    private Map<String, Double> costBreakdown;
    private Double totalCost;
    private String currency;
}
