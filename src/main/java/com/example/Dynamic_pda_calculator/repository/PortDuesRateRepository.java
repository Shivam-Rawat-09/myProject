package com.example.Dynamic_pda_calculator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Dynamic_pda_calculator.entity.PortDuesRate;

public interface PortDuesRateRepository extends JpaRepository<PortDuesRate, Integer> {
    Optional<PortDuesRate> findByPortNameAndVesselType(String portName, String vesselType);
}