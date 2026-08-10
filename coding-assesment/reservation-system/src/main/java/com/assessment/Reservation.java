package com.assessment;

import java.time.LocalDateTime;
import java.util.UUID;

import com.assessment.exceptions.InvalidReservation;

public class Reservation {
    private final UUID id = UUID.randomUUID();
    private final UUID resourceId;
    private final UUID employeeId;
    private LocalDateTime start;
    private LocalDateTime end;

    public Reservation(UUID resourceId, UUID employeeId, LocalDateTime start, LocalDateTime end) {
        this.checkReservationTime(start, end);
        this.resourceId = resourceId;
        this.employeeId = employeeId;
        this.start = start;
        this.end = end;
    }

    private void checkReservationTime(LocalDateTime start, LocalDateTime end) {
        if(end.isBefore(start)) {
            throw new InvalidReservation();
        }
    }

    public UUID getId() {
        return id;
    }

    public UUID getResourceId() {
        return resourceId;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }
}
