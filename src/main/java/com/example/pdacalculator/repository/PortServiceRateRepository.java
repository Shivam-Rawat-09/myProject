package com.example.pdacalculator.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.pdacalculator.model.Port;
import com.example.pdacalculator.model.PortServiceRate;

@Repository
public interface PortServiceRateRepository extends JpaRepository<PortServiceRate, Long> {
    
    
    List<PortServiceRate> findByPortId(Long portId);
    
    
    List<PortServiceRate> findByPort(Port port);
    
    
    Optional<PortServiceRate> findByPortIdAndServiceName(Long portId, String serviceName);
    
    
    List<PortServiceRate> findByCalculationBasis(String calculationBasis);
    
    
    List<PortServiceRate> findByPortIdAndCalculationBasis(Long portId, String calculationBasis);
    
    
    @Query("SELECT psr FROM PortServiceRate psr WHERE psr.port.id = :portId ORDER BY psr.serviceName")
    List<PortServiceRate> findAllServiceRatesForPort(@Param("portId") Long portId);
    
    
    boolean existsByPortIdAndServiceName(Long portId, String serviceName);
}
