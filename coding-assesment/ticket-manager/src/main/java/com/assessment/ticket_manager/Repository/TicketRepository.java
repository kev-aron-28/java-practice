package com.assessment.ticket_manager.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assessment.ticket_manager.Entities.Ticket;

/**
 * TicketRepository
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    public List<
}