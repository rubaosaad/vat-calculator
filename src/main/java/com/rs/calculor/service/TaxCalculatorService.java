package com.rs.calculor.service;

import com.rs.calculor.controller.TaxCalculatorDTO;
import com.rs.calculor.exceptionhandler.InvalidVatException;
import com.rs.calculor.exceptionhandler.ParametersExceededException;
import com.rs.calculor.exceptionhandler.TaxNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class TaxCalculatorService {


    public TaxCalculatorDTO calculate(TaxCalculatorDTO taxCalculatorDTO) {

        checkTaxPercentage(taxCalculatorDTO);
        countNumebs(taxCalculatorDTO);

        if (taxCalculatorDTO.getGrossTaxAmount() != null) {


            taxCalculatorDTO.setTaxAmount(taxCalculatorDTO.getGrossTaxAmount().multiply(taxCalculatorDTO.getTaxPercentage().divide(BigDecimal.valueOf(100))));

            taxCalculatorDTO.setTaxAmount(taxCalculatorDTO.getTaxAmount().setScale(2, BigDecimal.ROUND_HALF_UP));

            taxCalculatorDTO.setTotalAmount(taxCalculatorDTO.getGrossTaxAmount().add(taxCalculatorDTO.getTaxAmount()));

        }else if (taxCalculatorDTO.getTaxAmount() != null) {

            BigDecimal pGross = taxCalculatorDTO.getTaxAmount().divide(taxCalculatorDTO.getTaxPercentage().divide(BigDecimal.valueOf(100),2, BigDecimal.ROUND_HALF_UP),2, BigDecimal.ROUND_HALF_UP);

            taxCalculatorDTO.setGrossTaxAmount(pGross);

            taxCalculatorDTO.setTotalAmount(taxCalculatorDTO.getGrossTaxAmount().add(taxCalculatorDTO.getTaxAmount()));

        }else if (taxCalculatorDTO.getTotalAmount() != null) {

            BigDecimal vat = taxCalculatorDTO.getTotalAmount().divide(taxCalculatorDTO.getTaxPercentage().divide(BigDecimal.valueOf(100),2, BigDecimal.ROUND_HALF_UP).add(BigDecimal.valueOf(1)),2, BigDecimal.ROUND_HALF_UP);
            taxCalculatorDTO.setTaxAmount(taxCalculatorDTO.getTotalAmount().subtract(vat));
            taxCalculatorDTO.setGrossTaxAmount(vat);

        }

        return taxCalculatorDTO;
    }

    public void checkTaxPercentage(TaxCalculatorDTO taxCalculatorDTO){
        if (taxCalculatorDTO.getTaxPercentage() == null) {
            throw new TaxNotFoundException();
        }

        if (taxCalculatorDTO.getTaxPercentage().compareTo(BigDecimal.valueOf(10)) != 0
                && taxCalculatorDTO.getTaxPercentage().compareTo(BigDecimal.valueOf(13)) != 0
                && taxCalculatorDTO.getTaxPercentage().compareTo(BigDecimal.valueOf(20)) != 0 ) {
            throw new InvalidVatException();
        }
    }

    public void countNumebs(TaxCalculatorDTO taxCalculatorDTO){

        int count = 0;
        if (taxCalculatorDTO.getTaxAmount() != null) {
            count++;
        }
        if (taxCalculatorDTO.getGrossTaxAmount() != null) {
            count++;
        }
        if (taxCalculatorDTO.getTotalAmount() != null){
            count++;
        }

        if (count != 1) {
            throw new ParametersExceededException();
        }


    }



}
