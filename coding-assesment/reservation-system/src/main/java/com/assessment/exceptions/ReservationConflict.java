package com.assessment.exceptions;

public class ReservationConflict extends RuntimeException {

    public ReservationConflict() {
        super("Reservation time overlaps");
    }    
}
