package com.rs.calculor.controller;

import com.rs.calculor.service.TaxCalculatorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
@AllArgsConstructor
public class TaxCalculatorController {

    @Autowired
    TaxCalculatorService taxCalculatorService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaxCalculatorDTO> sendValues(@RequestBody TaxCalculatorDTO taxCalculatorDTO) {

        return ResponseEntity.ok(taxCalculatorService.calculate(taxCalculatorDTO));
    }

}
