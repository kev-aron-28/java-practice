package com.assessment;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import com.assessment.exceptions.InvalidReservation;

public class ReservationTest {
    
    @Test
    public void shouldThrowIfInvalidStartAndEnd() {
        assertThrows(InvalidReservation.class, () -> {
            LocalDateTime start = LocalDateTime.of(2026, 1, 20, 10, 20);
            LocalDateTime end = LocalDateTime.of(2026, 1, 20, 10, 10);
            new Reservation(UUID.randomUUID(), UUID.randomUUID(), start, end);
        });
    }
}
