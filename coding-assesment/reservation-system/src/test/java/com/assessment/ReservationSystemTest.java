package com.assessment;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.assessment.exceptions.ReservationConflict;
import com.assessment.exceptions.ResourceNotFound;

public class ReservationSystemTest {
   
    ReservationSystem system;

    @BeforeEach
    public void beforeEach() {
        system = new ReservationSystem();
    }

    @Test
    public void shouldCreateResource() {
        system.createResource("Laptop", ResourceType.COMPUTER);

        assertEquals(1., system.totalResources());
    }

    @Test
    public void shouldRemoveResource() {
        Resource resource = system.createResource("Laptop", ResourceType.COMPUTER);

        system.deleteResource(resource.getId());

        assertEquals(0, system.totalResources());
    }

    @Test
    public void shouldThrowIfResourceNotFound() {
        UUID id = UUID.randomUUID();

        assertThrows(ResourceNotFound.class, () -> {
            system.reserve(id, id, LocalDateTime.now(), LocalDateTime.now());
        });
    }

    @Test
    public void shouldAddReservation() {
        Resource resource = system.createResource("Laptop", ResourceType.COMPUTER);
        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 10, 10);
        LocalDateTime end = LocalDateTime.of(2026, 1, 1, 10, 20);

        system.reserve(resource.getId(), UUID.randomUUID(), start, end);

        assertEquals(1, system.totalReservations());
    } 

    @Test
    public void shouldRemoveReservation() {
        Resource resource = system.createResource("Laptop", ResourceType.COMPUTER);
        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 10, 10);
        LocalDateTime end = LocalDateTime.of(2026, 1, 1, 10, 20);

        Reservation r = system.reserve(resource.getId(), UUID.randomUUID(), start, end);

        system.cancelReservation(r.getId());

        assertEquals(0, system.totalReservations());
    } 

    @Test
    public void shouldThrowIfReservationOverlaps() {
        Resource resource = system.createResource("Laptop", ResourceType.COMPUTER);
        LocalDateTime start = LocalDateTime.of(2026, 1, 1, 10, 10);
        LocalDateTime end = LocalDateTime.of(2026, 1, 1, 10, 20);

        system.reserve(resource.getId(), UUID.randomUUID(), start, end);

        assertThrows(ReservationConflict.class, () -> {
            system.reserve(resource.getId(), UUID.randomUUID(), start, end);
        });
    }
}
