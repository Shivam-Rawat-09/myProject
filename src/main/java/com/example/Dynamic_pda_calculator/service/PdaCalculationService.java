package com.example.Dynamic_pda_calculator.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.Dynamic_pda_calculator.model.PdaForm;
import com.example.Dynamic_pda_calculator.model.PdaResult;
import com.example.Dynamic_pda_calculator.repository.BerthHireRateRepository;
import com.example.Dynamic_pda_calculator.repository.PilotageRateRepository;
import com.example.Dynamic_pda_calculator.repository.PortDuesRateRepository;
import com.example.Dynamic_pda_calculator.repository.WharfageRateRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PdaCalculationService {

    private final PortDuesRateRepository portDuesRateRepository;
    private final PilotageRateRepository pilotageRateRepository;
    private final BerthHireRateRepository berthHireRateRepository;
    private final WharfageRateRepository wharfageRateRepository;

    public PdaResult calculate(PdaForm form) {
        Map<String, Double> costBreakdown = new LinkedHashMap<>();
        boolean isForeign = "FOREIGN".equalsIgnoreCase(form.getVoyageType());
        String portName = form.getPortName(); // Get the selected port

        // 1. Port Dues Calculation
        portDuesRateRepository.findByPortNameAndVesselType(portName, form.getVesselType()).ifPresent(rate -> {
            double portDues = (isForeign ? rate.getForeignRateUsd() : rate.getCoastalRateInr()) * form.getGrt();
            costBreakdown.put("Port Dues", portDues);
        });

        // 2. Pilotage Fees Calculation
        pilotageRateRepository.findRateForPortAndGrt(portName, form.getVesselType(), form.getGrt()).ifPresent(rate -> {
            double pilotageFee = (isForeign ? rate.getForeignRateUsd() : rate.getCoastalRateInr()) * form.getGrt();
            costBreakdown.put("Pilotage Fees", pilotageFee);
        });

        // 3. Berth Hire Charges Calculation
        berthHireRateRepository.findByPortNameAndVesselType(portName, form.getVesselType()).ifPresent(rate -> {
            double berthHireCharge = (isForeign ? rate.getForeignRateUsdPerGrtHr() : rate.getCoastalRateInrPerGrtHr())
                                     * form.getGrt() * form.getBerthStayHours();
            costBreakdown.put("Berth Hire Charges", berthHireCharge);
        });

        // 4. Wharfage Calculation
        if (form.getCargoType() != null && !form.getCargoType().isEmpty() && form.getCargoQuantity() != null && form.getCargoQuantity() > 0) {
            wharfageRateRepository.findByPortNameAndCargoDescription(portName, form.getCargoType()).ifPresent(rate -> {
                double wharfageCharge = (isForeign ? rate.getForeignRateInr() : rate.getCoastalRateInr()) * form.getCargoQuantity();
                costBreakdown.put("Wharfage Charges", wharfageCharge);
            });
        }

        // Calculate Total
        double totalCost = costBreakdown.values().stream().mapToDouble(Double::doubleValue).sum();

        PdaResult result = new PdaResult();
        result.setInput(form);
        result.setCostBreakdown(costBreakdown);
        result.setTotalCost(totalCost);
        result.setCurrency(isForeign ? "USD" : "INR");

        if (isForeign) {
            result.setCurrency("USD (Note: Wharfage is in INR)");
        }

        return result;
    }
}
