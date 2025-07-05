package com.example.Dynamic_pda_calculator.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "wharfage_rates")
@Data
public class WharfageRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cargoDescription;
    private String unit;
    private Double foreignRateInr;
    private Double coastalRateInr;
    private String portName;
}
