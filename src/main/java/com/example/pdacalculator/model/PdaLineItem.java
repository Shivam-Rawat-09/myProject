package com.example.pdacalculator.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pda_line_items")
public class PdaLineItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pda_id", nullable = false)
    private Pda pda;
    
    @Column(name = "service_name", nullable = false, length = 255)
    private String serviceName;
    
    @Column(name = "calculation_notes", length = 255)
    private String calculationNotes;
    
    @Column(name = "cost", nullable = false, precision = 15, scale = 2)
    private BigDecimal cost;
    
    
    public PdaLineItem() {}
    
    
    public PdaLineItem(String serviceName, String calculationNotes, BigDecimal cost) {
        this.serviceName = serviceName;
        this.calculationNotes = calculationNotes;
        this.cost = cost;
    }
    
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Pda getPda() {
        return pda;
    }
    
    public void setPda(Pda pda) {
        this.pda = pda;
    }
    
    public String getServiceName() {
        return serviceName;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public String getCalculationNotes() {
        return calculationNotes;
    }
    
    public void setCalculationNotes(String calculationNotes) {
        this.calculationNotes = calculationNotes;
    }
    
    public BigDecimal getCost() {
        return cost;
    }
    
    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
    
    @Override
    public String toString() {
        return "PdaLineItem{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", calculationNotes='" + calculationNotes + '\'' +
                ", cost=" + cost +
                '}';
    }
}
