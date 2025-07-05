package com.example.Dynamic_pda_calculator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Dynamic_pda_calculator.entity.WharfageRate;

public interface WharfageRateRepository extends JpaRepository<WharfageRate, Integer> {
    Optional<WharfageRate> findByPortNameAndCargoDescription(String portName, String cargoDescription);
}
