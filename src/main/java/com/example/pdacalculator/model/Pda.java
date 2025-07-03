package com.example.pdacalculator.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "pdas")
public class Pda {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "port_id", nullable = false)
    private Port port;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vessel_id", nullable = false)
    private Vessel vessel;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo_type_id", nullable = false)
    private CargoType cargoType;
    
    @Column(name = "total_estimated_cost", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalEstimatedCost;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @OneToMany(mappedBy = "pda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PdaLineItem> pdaLineItems = new ArrayList<>();
    
    
    public Pda() {
        this.createdAt = LocalDateTime.now();
    }
    
    
    public Pda(Port port, Vessel vessel, CargoType cargoType, BigDecimal totalEstimatedCost) {
        this.port = port;
        this.vessel = vessel;
        this.cargoType = cargoType;
        this.totalEstimatedCost = totalEstimatedCost;
        this.createdAt = LocalDateTime.now();
    }
    
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Port getPort() {
        return port;
    }
    
    public void setPort(Port port) {
        this.port = port;
    }
    
    public Vessel getVessel() {
        return vessel;
    }
    
    public void setVessel(Vessel vessel) {
        this.vessel = vessel;
    }
    
    public CargoType getCargoType() {
        return cargoType;
    }
    
    public void setCargoType(CargoType cargoType) {
        this.cargoType = cargoType;
    }
    
    public BigDecimal getTotalEstimatedCost() {
        return totalEstimatedCost;
    }
    
    public void setTotalEstimatedCost(BigDecimal totalEstimatedCost) {
        this.totalEstimatedCost = totalEstimatedCost;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public List<PdaLineItem> getPdaLineItems() {
        return pdaLineItems;
    }
    
    public void setPdaLineItems(List<PdaLineItem> pdaLineItems) {
        this.pdaLineItems = pdaLineItems;
    }
    
    
    public void addPdaLineItem(PdaLineItem lineItem) {
        pdaLineItems.add(lineItem);
        lineItem.setPda(this);
    }
    
    
    public void removePdaLineItem(PdaLineItem lineItem) {
        pdaLineItems.remove(lineItem);
        lineItem.setPda(null);
    }
    
    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
    
    @Override
    public String toString() {
        return "Pda{" +
                "id=" + id +
                ", port=" + (port != null ? port.getName() : "null") +
                ", vessel=" + (vessel != null ? vessel.getName() : "null") +
                ", cargoType=" + (cargoType != null ? cargoType.getName() : "null") +
                ", totalEstimatedCost=" + totalEstimatedCost +
                ", createdAt=" + createdAt +
                ", lineItemsCount=" + (pdaLineItems != null ? pdaLineItems.size() : 0) +
                '}';
    }
}
