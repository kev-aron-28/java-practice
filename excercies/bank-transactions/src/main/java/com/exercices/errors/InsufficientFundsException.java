package com.exercices.errors;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException() {
        super("Not enough funds in the account");
    }
    
}
