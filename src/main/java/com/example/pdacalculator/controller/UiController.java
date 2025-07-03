package com.example.pdacalculator.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.pdacalculator.model.CargoType;
import com.example.pdacalculator.model.Pda;
import com.example.pdacalculator.model.Port;
import com.example.pdacalculator.model.PortServiceRate;
import com.example.pdacalculator.model.Vessel;
import com.example.pdacalculator.repository.CargoTypeRepository;
import com.example.pdacalculator.repository.PortRepository;
import com.example.pdacalculator.repository.PortServiceRateRepository;
import com.example.pdacalculator.repository.VesselRepository;
import com.example.pdacalculator.service.PdaCalculationRequest;
import com.example.pdacalculator.service.PdaCalculationService;

@Controller
public class UiController {
    private final PortRepository portRepository;
    private final VesselRepository vesselRepository;
    private final CargoTypeRepository cargoTypeRepository;
    private final PortServiceRateRepository portServiceRateRepository;
    private final PdaCalculationService pdaCalculationService;

    public UiController(PortRepository portRepository,
                        VesselRepository vesselRepository,
                        CargoTypeRepository cargoTypeRepository,
                        PortServiceRateRepository portServiceRateRepository,
                        PdaCalculationService pdaCalculationService) {
        this.portRepository = portRepository;
        this.vesselRepository = vesselRepository;
        this.cargoTypeRepository = cargoTypeRepository;
        this.portServiceRateRepository = portServiceRateRepository;
        this.pdaCalculationService = pdaCalculationService;
    }

    @GetMapping("/")
    public String showCalculatorPage(Model model) {
        List<Port> ports = portRepository.findAll();
        List<Vessel> vessels = vesselRepository.findAll();
        List<CargoType> cargoTypes = cargoTypeRepository.findAll();
        List<PortServiceRate> serviceRates = Collections.emptyList();
        if (!ports.isEmpty()) {
            serviceRates = portServiceRateRepository.findByPortId(ports.get(0).getId());
        }
        model.addAttribute("ports", ports);
        model.addAttribute("vessels", vessels);
        model.addAttribute("cargoTypes", cargoTypes);
        model.addAttribute("serviceRates", serviceRates);
        return "calculator";
    }

    @PostMapping("/calculate")
    public String calculatePdaResult(@RequestParam Long portId,
                                     @RequestParam Long vesselId,
                                     @RequestParam Long cargoTypeId,
                                     @RequestParam Map<String, String> allParams,
                                     Model model) {
       
        Map<String, Double> overrides = new HashMap<>();
        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith("overrides[")) {
                String serviceName = key.substring(10, key.length() - 1); 
                try {
                    overrides.put(serviceName, Double.parseDouble(entry.getValue()));
                } catch (NumberFormatException e) {
                    
                }
            }
        }
        PdaCalculationRequest request = new PdaCalculationRequest();
        request.setPortId(portId);
        request.setVesselId(vesselId);
        request.setCargoTypeId(cargoTypeId);
        request.setOverrides(overrides);
        Pda pda = pdaCalculationService.calculateAndSavePda(request);
        model.addAttribute("pda", pda);
        return "pda-result";
    }
} 