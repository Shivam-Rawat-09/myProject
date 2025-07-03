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
@Table(name = "port_service_rates")
public class PortServiceRate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "port_id", nullable = false)
    private Port port;
    
    @Column(name = "service_name", nullable = false, length = 255)
    private String serviceName;
    
    @Column(name = "calculation_basis", nullable = false, length = 50)
    private String calculationBasis;
    
    @Column(name = "rate", nullable = false, precision = 10, scale = 2)
    private BigDecimal rate;
    
    
    public PortServiceRate() {}
    
    
    public PortServiceRate(Port port, String serviceName, String calculationBasis, BigDecimal rate) {
        this.port = port;
        this.serviceName = serviceName;
        this.calculationBasis = calculationBasis;
        this.rate = rate;
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
    
    public String getServiceName() {
        return serviceName;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public String getCalculationBasis() {
        return calculationBasis;
    }
    
    public void setCalculationBasis(String calculationBasis) {
        this.calculationBasis = calculationBasis;
    }
    
    public BigDecimal getRate() {
        return rate;
    }
    
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }
    
    @Override
    public String toString() {
        return "PortServiceRate{" +
                "id=" + id +
                ", port=" + (port != null ? port.getName() : "null") +
                ", serviceName='" + serviceName + '\'' +
                ", calculationBasis='" + calculationBasis + '\'' +
                ", rate=" + rate +
                '}';
    }
}
