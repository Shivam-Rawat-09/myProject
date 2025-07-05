package com.example.Dynamic_pda_calculator.model;

import lombok.Data;

@Data
public class PdaForm {
    private String portName = "CHENNAI"; // Default value
    private String voyageType = "FOREIGN"; // FOREIGN or COASTAL
    private String vesselType; // e.g., CONTAINER, POL_CRUDE
    private Integer grt;
    private Integer berthStayHours;
    private String cargoType;
    private Double cargoQuantity;
}
