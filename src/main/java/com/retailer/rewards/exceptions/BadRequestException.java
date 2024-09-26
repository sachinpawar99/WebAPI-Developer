package com.retailer.rewards.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private double fieldValue;

    public BadRequestException(String resourceName, String fieldName,double fieldValue)
    {
        super(String.format("%s %s : '%s'",resourceName,fieldName,fieldValue));
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }
}
