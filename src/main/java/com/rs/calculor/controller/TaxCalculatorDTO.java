package com.rs.calculor.controller;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;


@Data
@Builder
public class TaxCalculatorDTO {

    private BigDecimal taxPercentage;
    private BigDecimal grossTaxAmount;
    private BigDecimal taxAmount;
    private BigDecimal totalAmount;


    public void getTaxPercentage(BigDecimal valueOf) {
    }

    public void getGrossTaxAmount(BigDecimal valueOf) {
    }
}
