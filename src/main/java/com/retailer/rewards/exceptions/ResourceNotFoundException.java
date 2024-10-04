package com.retailer.rewards.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class to handle ResourceNotFoundException request scenarios.
 * This exception is thrown when a requested resource cannot be found.
 * The exception will result in a response with HTTP status code 404 (Not Found).
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private Long fieldValue;

    /**
     * Constructs a new ResourceNotFoundException with a detailed message that includes
     * the resource name, field name, and field value that was not found.
     *
     * @param resourceName the name of the resource that could not be found
     * @param fieldName the name of the field that is associated with the missing resource
     * @param fieldValue the value of the field that is not found
     */
    public ResourceNotFoundException(String resourceName, String fieldName,Long fieldValue)
    {
        super(String.format("%s %s : '%s'",resourceName,fieldName,fieldValue));

        this.resourceName=resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }
}