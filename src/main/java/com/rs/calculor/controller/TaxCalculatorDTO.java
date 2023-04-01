package com.rs.calculor.controller;

import lombok.Data;

@Data
public class TaxCalculatorDTO {

    private double taxPercentage;
    private double grossTaxAmount;
    private double taxAmount;
    private double totalAmount;


}
