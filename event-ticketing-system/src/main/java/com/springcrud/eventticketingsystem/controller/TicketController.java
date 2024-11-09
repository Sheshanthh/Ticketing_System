package com.springcrud.eventticketingsystem.controller;

import com.springcrud.eventticketingsystem.model.Ticket;
import com.springcrud.eventticketingsystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // Purchase a ticket
    @PostMapping("/purchase")
    public ResponseEntity<Ticket> purchaseTicket() {
        Ticket ticket = ticketService.purchaseTicket();
        if (ticket == null) {
            // No tickets available, return a 400 Bad Request
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // Successfully purchased a ticket, return ticket with 200 OK status
        return new ResponseEntity<>(ticket, HttpStatus.OK);
    }

    // Get current ticket count
    @GetMapping("/count")
    public ResponseEntity<Integer> getTicketCount() {
        int ticketCount = ticketService.getTicketCount();
        return new ResponseEntity<>(ticketCount, HttpStatus.OK);
    }

    // Add tickets to the pool (for debugging and testing purposes)
    @PostMapping("/add")
    public ResponseEntity<String> addTickets(@RequestParam int numTickets) {
        if (numTickets <= 0) {
            // Invalid input: Number of tickets to add must be greater than zero
            return new ResponseEntity<>("Number of tickets to add must be greater than zero.", HttpStatus.BAD_REQUEST);
        }
        // Add tickets to the pool
        ticketService.addTickets(numTickets);
        // Log the ticket addition (Optional for debugging purposes)
        return new ResponseEntity<>("Successfully added " + numTickets + " tickets.", HttpStatus.OK);
    }
}
