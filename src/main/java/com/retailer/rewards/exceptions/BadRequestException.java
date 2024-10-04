package com.retailer.rewards.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class to handle bad request scenarios.
 * This exception is thrown when the client sends a request that is invalid or cannot be processed.
 * The exception will result in a response with HTTP status code 400 (Bad Request).
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private double fieldValue;

    /**
     * Constructs a new BadRequestException with a detailed message that includes
     * the resource name, field name, and field value that caused the bad request.
     *
     * @param resourceName the name of the resource that triggered the exception
     * @param fieldName the name of the field that is invalid or causing the issue
     * @param fieldValue the value of the field that is causing the bad request
     */
    public BadRequestException(String resourceName, String fieldName,double fieldValue)
    {
        super(String.format("%s %s : '%s'",resourceName,fieldName,fieldValue));
        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }
}
