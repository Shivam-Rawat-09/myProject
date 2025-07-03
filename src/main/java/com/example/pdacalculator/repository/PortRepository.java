package com.example.pdacalculator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pdacalculator.model.Port;

@Repository
public interface PortRepository extends JpaRepository<Port, Long> {
    
    
    Optional<Port> findByName(String name);
    
    
    java.util.List<Port> findByCountry(String country);
    
    
    boolean existsByName(String name);
}
