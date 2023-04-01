package com.rs.calculor.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MessageExceptionHandler{

    private Date timestamp;
    private Integer status;
    private String message;



}
