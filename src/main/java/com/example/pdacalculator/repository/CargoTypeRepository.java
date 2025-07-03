package com.example.pdacalculator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pdacalculator.model.CargoType;

@Repository
public interface CargoTypeRepository extends JpaRepository<CargoType, Long> {
    
    
    Optional<CargoType> findByName(String name);
    
    
    boolean existsByName(String name);
    
    
    java.util.List<CargoType> findByNameContainingIgnoreCase(String name);
}
