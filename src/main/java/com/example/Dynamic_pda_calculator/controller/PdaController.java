package com.example.Dynamic_pda_calculator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Dynamic_pda_calculator.model.PdaForm;
import com.example.Dynamic_pda_calculator.model.PdaResult;
import com.example.Dynamic_pda_calculator.service.PdaCalculationService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PdaController {

    private final PdaCalculationService pdaCalculationService;

    @GetMapping("/")
    public String showCalculatorForm(Model model) {
        model.addAttribute("pdaForm", new PdaForm());
        return "calculator";
    }

    @PostMapping("/calculate")
    public String calculatePda(@ModelAttribute PdaForm pdaForm, Model model) {
        PdaResult result = pdaCalculationService.calculate(pdaForm);
        model.addAttribute("result", result);
        return "result";
    }
}
