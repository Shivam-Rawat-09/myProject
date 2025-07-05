package com.example.Dynamic_pda_calculator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Dynamic_pda_calculator.entity.PilotageRate;

public interface PilotageRateRepository extends JpaRepository<PilotageRate, Integer> {
    @Query("SELECT r FROM PilotageRate r WHERE r.portName = ?1 AND r.vesselType = ?2 AND r.grtMin <= ?3 AND r.grtMax >= ?3")
    Optional<PilotageRate> findRateForPortAndGrt(String portName, String vesselType, Integer grt);
}