package com.assessment.exceptions;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound() {
        super("The resource was not found");
    }
    
}
