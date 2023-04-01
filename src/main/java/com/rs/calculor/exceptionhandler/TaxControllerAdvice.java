package com.rs.calculor.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@ControllerAdvice(basePackages = "com.rs.calculor.controller")
public class TaxControllerAdvice {

    @ResponseBody
    @ExceptionHandler(TaxNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> taxNotFound(TaxNotFoundException taxNotFound){

        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(), "Tax percentage is mandatory");

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }


    @ResponseBody
    @ExceptionHandler(ParametersExceededException.class)
    public ResponseEntity<MessageExceptionHandler> parametersExceeded(ParametersExceededException parametersExceededException){

        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), "Pass only one of the values: grossTaxAmount, totalAmount or taxAmount");

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

    @ResponseBody
    @ExceptionHandler(InvalidVatException.class)
    public ResponseEntity<MessageExceptionHandler> invalidVatException(InvalidVatException invalidVatException){

        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), "Invalid Vat. Needs to be: 10, 13 or 20");

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

}
