package com.rs.calculor.service;

import com.rs.calculor.controller.TaxCalculatorDTO;
import com.rs.calculor.exceptionhandler.InvalidVatException;
import com.rs.calculor.exceptionhandler.ParametersExceededException;
import com.rs.calculor.exceptionhandler.TaxNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TaxCalculatorService {


    public TaxCalculatorDTO calculate(TaxCalculatorDTO taxCalculatorDTO) {

        if (taxCalculatorDTO.getTaxPercentage()  == 0 ) {
            throw new TaxNotFoundException();
        }


        if (taxCalculatorDTO.getTaxPercentage() != 10 && taxCalculatorDTO.getTaxPercentage() != 13 && taxCalculatorDTO.getTaxPercentage() != 20 ) {
            throw new InvalidVatException();
        }

        countNumebs(taxCalculatorDTO);

        if (taxCalculatorDTO.getGrossTaxAmount() != 0) {

            taxCalculatorDTO.setTaxAmount(taxCalculatorDTO.getGrossTaxAmount()
                    * taxCalculatorDTO.getTaxPercentage() / 100.0);

            taxCalculatorDTO.setTotalAmount(taxCalculatorDTO.getGrossTaxAmount()
                    + taxCalculatorDTO.getTaxAmount());

        }else if (taxCalculatorDTO.getTaxAmount() != 0) {

            double pGross = taxCalculatorDTO.getTaxAmount() /
                    taxCalculatorDTO.getTaxPercentage() + taxCalculatorDTO.getTaxAmount();
            double pTotal = pGross /
                    (1 + taxCalculatorDTO.getTaxPercentage());

            taxCalculatorDTO.setGrossTaxAmount(pTotal * 100);

            taxCalculatorDTO.setTotalAmount(taxCalculatorDTO.getGrossTaxAmount()
                    +taxCalculatorDTO.getTaxAmount());

        }else if (taxCalculatorDTO.getTotalAmount() != 0) {

            taxCalculatorDTO.setTaxAmount(taxCalculatorDTO.getTotalAmount() /
                    (1 + (taxCalculatorDTO.getTaxPercentage() / 100))
                    * (taxCalculatorDTO.getTaxPercentage() / 100));

            taxCalculatorDTO.setGrossTaxAmount(taxCalculatorDTO.getTotalAmount()
                    - taxCalculatorDTO.getTaxAmount());
        }

        return taxCalculatorDTO;
    }


    public void countNumebs(TaxCalculatorDTO taxCalculatorDTO){

        int count = 0;
        if (taxCalculatorDTO.getTaxAmount() != 0) {
            count++;
        }
        if (taxCalculatorDTO.getGrossTaxAmount() != 0) {
            count++;
        }
        if (taxCalculatorDTO.getTotalAmount() != 0) {
            count++;
        }

        if (count != 1) {
            throw new ParametersExceededException();
        }


    }



}
