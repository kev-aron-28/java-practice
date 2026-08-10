package com.assessment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.assessment.exceptions.ReservationConflict;
import com.assessment.exceptions.ResourceNotFound;

public class ReservationSystem {
    Map<UUID, Resource> resources = new HashMap<>();
    List<Reservation> reservations = new ArrayList<>();
    
    public Resource createResource(String name, ResourceType type) {
        UUID id = UUID.randomUUID();

        Resource resource = new Resource(id, name, type);

        resources.put(id, resource);

        return resource;
    }

    public void deleteResource(UUID resourceId) {
        resources.remove(resourceId);
    }

    public Reservation reserve(
        UUID resourceId,
        UUID employeeId,
        LocalDateTime start,
        LocalDateTime end
    ) {
        if(!resources.containsKey(resourceId)) {
            throw new ResourceNotFound();
        }

        List<Reservation> resourceReservations = reservations
        .stream()
        .filter(r -> r.getResourceId().equals(resourceId)).toList();

        for(Reservation r : resourceReservations) {
            boolean overlaps = start.isBefore(r.getEnd()) && end.isAfter(r.getStart());

            if(overlaps) throw new ReservationConflict();
        }

        Reservation res = new Reservation(resourceId, employeeId, start, end);
        
        reservations.add(res);

        return res;
    }

    public List<Reservation> getReservations(UUID resourceId) {
        return reservations
            .stream()
            .filter(r -> r.getId().equals(resourceId))
            .toList();
    }

    public void cancelReservation(UUID reservationId) {
        reservations.removeIf(r -> r.getId().equals(reservationId));
    }

    public int totalResources() {
        return resources.size();
    }
    
    public int totalReservations() {
        return reservations.size();
    }
}
