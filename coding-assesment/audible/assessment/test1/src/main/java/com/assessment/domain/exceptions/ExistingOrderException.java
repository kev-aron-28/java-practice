package com.assessment.domain.exceptions;

public class ExistingOrderException extends DomainException {

    public ExistingOrderException(String orderId) {
        super("The order with the id already exists: " + orderId);
    }
    
}
