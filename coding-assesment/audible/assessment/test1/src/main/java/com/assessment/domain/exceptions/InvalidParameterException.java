package com.assessment.domain.exceptions;

public class InvalidParameterException extends DomainException {
    public InvalidParameterException(String parameter) {
        super("The parameter is incorrect:" + parameter);
    }
}
