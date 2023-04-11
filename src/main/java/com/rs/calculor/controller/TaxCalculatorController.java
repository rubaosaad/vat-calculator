package com.rs.calculor.controller;

import com.rs.calculor.service.TaxCalculatorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple API to VAT amounts for purchases in Austria so
 * that I can use correctly calculated purchase data.
 *
 * @author Rubens Saad
 * @version 1.0
 */
@RestController
@RequestMapping("/calculator")
public class TaxCalculatorController {


    TaxCalculatorService taxCalculatorService;
    @Autowired
    public TaxCalculatorController(TaxCalculatorService taxCalculatorService) {
        this.taxCalculatorService = taxCalculatorService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Tag(name = "CalVat", description = "API to calculate VAT values for different pass parameters")
    public ResponseEntity<TaxCalculatorDTO> sendValues(@RequestBody TaxCalculatorDTO taxCalculatorDTO) {

        return ResponseEntity.ok(taxCalculatorService.calculate(taxCalculatorDTO));
    }

}
