package com.example.pdacalculator.service;

import java.util.Map;

public class PdaCalculationRequest {
    private Long portId;
    private Long vesselId;
    private Long cargoTypeId;
    private Map<String, Double> overrides;

    public Long getPortId() {
        return portId;
    }

    public void setPortId(Long portId) {
        this.portId = portId;
    }

    public Long getVesselId() {
        return vesselId;
    }

    public void setVesselId(Long vesselId) {
        this.vesselId = vesselId;
    }

    public Long getCargoTypeId() {
        return cargoTypeId;
    }

    public void setCargoTypeId(Long cargoTypeId) {
        this.cargoTypeId = cargoTypeId;
    }

    public Map<String, Double> getOverrides() {
        return overrides;
    }

    public void setOverrides(Map<String, Double> overrides) {
        this.overrides = overrides;
    }
}
