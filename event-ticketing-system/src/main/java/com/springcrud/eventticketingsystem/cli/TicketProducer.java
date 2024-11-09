package com.springcrud.eventticketingsystem.cli;

import com.springcrud.eventticketingsystem.model.Ticket;

public class TicketProducer extends Thread {

    private final TicketPool ticketPool;
    private final int ticketsToAdd;

    public TicketProducer(TicketPool ticketPool, int ticketsToAdd) {
        this.ticketPool = ticketPool;
        this.ticketsToAdd = ticketsToAdd;
    }

    @Override
    public void run() {
        ticketPool.addTickets(ticketsToAdd);
    }
}
