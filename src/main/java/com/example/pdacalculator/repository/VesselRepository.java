package com.example.pdacalculator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pdacalculator.model.Vessel;

@Repository
public interface VesselRepository extends JpaRepository<Vessel, Long> {
    
    
    Optional<Vessel> findByName(String name);
    
    
    java.util.List<Vessel> findByType(String type);
    
    
    boolean existsByName(String name);
    
    
    java.util.List<Vessel> findByDwtBetween(Integer minDwt, Integer maxDwt);
    
    
    java.util.List<Vessel> findByGrossTonnageBetween(Integer minGt, Integer maxGt);
}
