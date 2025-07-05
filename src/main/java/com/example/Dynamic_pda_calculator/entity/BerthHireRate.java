package com.example.Dynamic_pda_calculator.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "berth_hire_rates")
@Data
public class BerthHireRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String vesselType;
    private Double foreignRateUsdPerGrtHr;
    private Double coastalRateInrPerGrtHr;
    private String portName;
}
