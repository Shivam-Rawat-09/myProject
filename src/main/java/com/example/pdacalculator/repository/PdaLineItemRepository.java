package com.example.pdacalculator.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.pdacalculator.model.PdaLineItem;

@Repository
public interface PdaLineItemRepository extends JpaRepository<PdaLineItem, Long> {
    
    
    List<PdaLineItem> findByPdaId(Long pdaId);
    
    
    List<PdaLineItem> findByServiceName(String serviceName);
    
    
    List<PdaLineItem> findByPdaIdAndServiceName(Long pdaId, String serviceName);
    
    
    List<PdaLineItem> findByCostGreaterThan(BigDecimal amount);
    
    
    List<PdaLineItem> findByCostBetween(BigDecimal minAmount, BigDecimal maxAmount);
    
    
    @Query("SELECT SUM(pli.cost) FROM PdaLineItem pli WHERE pli.pda.id = :pdaId")
    BigDecimal calculateTotalCostForPda(@Param("pdaId") Long pdaId);
    
    
    @Query("SELECT pli FROM PdaLineItem pli WHERE pli.pda.id = :pdaId ORDER BY pli.cost DESC")
    List<PdaLineItem> findMostExpensiveLineItemsForPda(@Param("pdaId") Long pdaId);
    
    
    void deleteByPdaId(Long pdaId);
}
