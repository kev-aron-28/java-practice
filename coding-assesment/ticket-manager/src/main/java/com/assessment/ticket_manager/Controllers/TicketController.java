package com.assessment.ticket_manager.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.ticket_manager.DTO.CreateTicketDTO;
import com.assessment.ticket_manager.Entities.Ticket;
import com.assessment.ticket_manager.Service.TicketService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/tickets")
@Validated
public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<Ticket> createTicket(@RequestBody @Valid CreateTicketDTO entity) {
        Ticket ticket = service.createTicket(entity);

        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }
    
    @GetMapping("path")
    public ResponseEntity<List<Ticket>> getTickets(@RequestParam(required = false) String query) {
        List<Ticket> tickets = new 
        return 
    }
    
}
