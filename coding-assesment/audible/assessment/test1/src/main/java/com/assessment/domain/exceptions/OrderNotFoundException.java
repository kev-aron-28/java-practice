package com.assessment.domain.exceptions;

public class OrderNotFoundException extends DomainException {
    public OrderNotFoundException(String id) {
        super("Order with id: " + id + " not found");
    }
}
