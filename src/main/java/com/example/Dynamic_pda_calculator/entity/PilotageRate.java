package com.example.Dynamic_pda_calculator.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "pilotage_rates")
@Data
public class PilotageRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer grtMin;
    private Integer grtMax;
    private String vesselType;
    private Double foreignRateUsd;
    private Double coastalRateInr;
    private String portName;
}