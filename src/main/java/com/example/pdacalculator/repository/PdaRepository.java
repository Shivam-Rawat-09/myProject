package com.example.pdacalculator.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.pdacalculator.model.Pda;

@Repository
public interface PdaRepository extends JpaRepository<Pda, Long> {
    
    
    List<Pda> findByPortId(Long portId);
    
    
    List<Pda> findByVesselId(Long vesselId);
    
    
    List<Pda> findByCargoTypeId(Long cargoTypeId);
    
    
    List<Pda> findByPortIdAndVesselIdAndCargoTypeId(Long portId, Long vesselId, Long cargoTypeId);
    
    
    List<Pda> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    
    List<Pda> findByCreatedAtAfterOrderByCreatedAtDesc(LocalDateTime date);
    
    
    @Query("SELECT DISTINCT p FROM Pda p LEFT JOIN FETCH p.pdaLineItems WHERE p.id = :pdaId")
    Pda findByIdWithLineItems(@Param("pdaId") Long pdaId);
    
    
    @Query("SELECT p FROM Pda p WHERE p.port.id = :portId ORDER BY p.createdAt DESC")
    List<Pda> findRecentPdasByPort(@Param("portId") Long portId);
}
