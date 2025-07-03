package com.example.pdacalculator.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vessels")
public class Vessel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false, unique = true, length = 255)
    private String name;
    
    @Column(name = "type", nullable = false, length = 100)
    private String type;
    
    @Column(name = "dwt", nullable = false)
    private Integer dwt;
    
    @Column(name = "gross_tonnage", nullable = false)
    private Integer grossTonnage;
    
    @Column(name = "net_tonnage", nullable = false)
    private Integer netTonnage;
    
    
    public Vessel() {}
    
    
    public Vessel(String name, String type, Integer dwt, Integer grossTonnage, Integer netTonnage) {
        this.name = name;
        this.type = type;
        this.dwt = dwt;
        this.grossTonnage = grossTonnage;
        this.netTonnage = netTonnage;
    }
    
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Integer getDwt() {
        return dwt;
    }
    
    public void setDwt(Integer dwt) {
        this.dwt = dwt;
    }
    
    public Integer getGrossTonnage() {
        return grossTonnage;
    }
    
    public void setGrossTonnage(Integer grossTonnage) {
        this.grossTonnage = grossTonnage;
    }
    
    public Integer getNetTonnage() {
        return netTonnage;
    }
    
    public void setNetTonnage(Integer netTonnage) {
        this.netTonnage = netTonnage;
    }
    
    @Override
    public String toString() {
        return "Vessel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", dwt=" + dwt +
                ", grossTonnage=" + grossTonnage +
                ", netTonnage=" + netTonnage +
                '}';
    }
}
