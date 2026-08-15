package com.assessment.ticket_manager.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assessment.ticket_manager.DTO.CreateTicketDTO;
import com.assessment.ticket_manager.DTO.GetTicketsDTO;
import com.assessment.ticket_manager.Entities.Ticket;
import com.assessment.ticket_manager.Repository.TicketRepository;

@Service
public class TicketService {
    
    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    public Ticket createTicket(CreateTicketDTO dto) {
        Ticket ticket = new Ticket(
            dto.title(),
            dto.priority()
        );

        return repository.save(ticket);
    }

    public List<Ticket> findAllTickets(GetTicketsDTO dto) {
        if(!dto.query().isEmpty()) {
            return repository.findByQuery(dto.query());
        }

        return repository.findAll();
    }
}
