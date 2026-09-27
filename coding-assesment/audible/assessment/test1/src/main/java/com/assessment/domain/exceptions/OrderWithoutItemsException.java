package com.assessment.domain.exceptions;

public class OrderWithoutItemsException extends DomainException {

    public OrderWithoutItemsException() {
        super("An order must contain items");
    }
    
}
