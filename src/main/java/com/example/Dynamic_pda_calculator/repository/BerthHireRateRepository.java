package com.example.Dynamic_pda_calculator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Dynamic_pda_calculator.entity.BerthHireRate;

public interface BerthHireRateRepository extends JpaRepository<BerthHireRate, Integer> {
    Optional<BerthHireRate> findByPortNameAndVesselType(String portName, String vesselType);
}
