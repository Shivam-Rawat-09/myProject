package com.example.pdacalculator.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.pdacalculator.model.CargoType;
import com.example.pdacalculator.model.Pda;
import com.example.pdacalculator.model.PdaLineItem;
import com.example.pdacalculator.model.Port;
import com.example.pdacalculator.model.PortServiceRate;
import com.example.pdacalculator.model.Vessel;
import com.example.pdacalculator.repository.CargoTypeRepository;
import com.example.pdacalculator.repository.PdaRepository;
import com.example.pdacalculator.repository.PortRepository;
import com.example.pdacalculator.repository.PortServiceRateRepository;
import com.example.pdacalculator.repository.VesselRepository;

@Service
public class PdaCalculationServiceImpl implements PdaCalculationService {
    private final PortRepository portRepository;
    private final VesselRepository vesselRepository;
    private final PortServiceRateRepository portServiceRateRepository;
    private final PdaRepository pdaRepository;
    private final CargoTypeRepository cargoTypeRepository;

    public PdaCalculationServiceImpl(PortRepository portRepository,
                                     VesselRepository vesselRepository,
                                     PortServiceRateRepository portServiceRateRepository,
                                     PdaRepository pdaRepository,
                                     CargoTypeRepository cargoTypeRepository) {
        this.portRepository = portRepository;
        this.vesselRepository = vesselRepository;
        this.portServiceRateRepository = portServiceRateRepository;
        this.pdaRepository = pdaRepository;
        this.cargoTypeRepository = cargoTypeRepository;
    }

    @Override
    public Pda calculateAndSavePda(PdaCalculationRequest request) {
        // Input validation
        if (request.getPortId() == null || request.getPortId() <= 0)
            throw new IllegalArgumentException("Invalid or missing portId");
        if (request.getVesselId() == null || request.getVesselId() <= 0)
            throw new IllegalArgumentException("Invalid or missing vesselId");
        if (request.getCargoTypeId() == null || request.getCargoTypeId() <= 0)
            throw new IllegalArgumentException("Invalid or missing cargoTypeId");

        Port port = portRepository.findById(request.getPortId())
                .orElseThrow(() -> new RuntimeException("Port not found with id: " + request.getPortId()));
        Vessel vessel = vesselRepository.findById(request.getVesselId())
                .orElseThrow(() -> new RuntimeException("Vessel not found with id: " + request.getVesselId()));
        CargoType cargoType = cargoTypeRepository.findById(request.getCargoTypeId())
                .orElseThrow(() -> new RuntimeException("CargoType not found with id: " + request.getCargoTypeId()));

        // Vessel tonnage validation
        if (vessel.getGrossTonnage() == null || vessel.getGrossTonnage() < 0)
            throw new IllegalArgumentException("Vessel gross tonnage is missing or negative");
        if (vessel.getDwt() == null || vessel.getDwt() < 0)
            throw new IllegalArgumentException("Vessel DWT is missing or negative");
        if (vessel.getNetTonnage() == null || vessel.getNetTonnage() < 0)
            throw new IllegalArgumentException("Vessel net tonnage is missing or negative");

        List<PortServiceRate> serviceRates = portServiceRateRepository.findByPortId(request.getPortId());
        Map<String, Double> overrides = request.getOverrides();

        // Validate overrides
        if (overrides != null) {
            for (Map.Entry<String, Double> entry : overrides.entrySet()) {
                if (entry.getValue() == null || entry.getValue() < 0) {
                    throw new IllegalArgumentException("Override for service '" + entry.getKey() + "' is missing or negative");
                }
            }
        }

        Pda pda = new Pda();
        pda.setPort(port);
        pda.setVessel(vessel);
        pda.setCargoType(cargoType);

        BigDecimal totalCost = BigDecimal.ZERO;

        for (PortServiceRate rate : serviceRates) {
            String serviceName = rate.getServiceName();
            BigDecimal usedRate = rate.getRate();
            if (overrides != null && overrides.containsKey(serviceName)) {
                usedRate = BigDecimal.valueOf(overrides.get(serviceName));
            }
            BigDecimal cost = BigDecimal.ZERO;
            String note = "";
            switch (rate.getCalculationBasis()) {
                case "FLAT":
                    cost = usedRate;
                    note = "Flat rate";
                    break;
                case "PER_GT":
                    cost = usedRate.multiply(BigDecimal.valueOf(vessel.getGrossTonnage()));
                    note = "Per GT: " + vessel.getGrossTonnage();
                    break;
                case "PER_DWT":
                    cost = usedRate.multiply(BigDecimal.valueOf(vessel.getDwt()));
                    note = "Per DWT: " + vessel.getDwt();
                    break;
                case "PER_NT":
                    cost = usedRate.multiply(BigDecimal.valueOf(vessel.getNetTonnage()));
                    note = "Per NT: " + vessel.getNetTonnage();
                    break;
                default:
                    note = "Unknown calculation basis";
            }
            PdaLineItem lineItem = new PdaLineItem(serviceName, note, cost);
            pda.addPdaLineItem(lineItem);
            totalCost = totalCost.add(cost);
        }

        pda.setTotalEstimatedCost(totalCost);
        Pda savedPda = pdaRepository.save(pda);
        return savedPda;
    }
} 