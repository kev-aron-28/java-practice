package com.assessment.exceptions;

/**
 * InvalidReservation
 */
public class InvalidReservation extends RuntimeException {
    public InvalidReservation() {
        super("Invalid start and end time");
    }
}