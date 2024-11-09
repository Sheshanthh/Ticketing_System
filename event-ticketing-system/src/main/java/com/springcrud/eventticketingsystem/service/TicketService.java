package com.springcrud.eventticketingsystem.service;

import com.springcrud.eventticketingsystem.model.Ticket;
import org.springframework.stereotype.Service;


@Service
public class TicketService {

    private static int totalTickets = ConfigService.getTotalTickets();  // Starting with user-defined tickets

    // Add tickets to the system
    public static void addTickets(int numTickets) {
        if (totalTickets + numTickets <= ConfigService.getMaxTicketCapacity()) {
            totalTickets += numTickets;
            System.out.println(numTickets + " tickets added. Total tickets: " + totalTickets);
        } else {
            System.out.println("Cannot add more tickets. Max capacity reached.");
        }
    }

    // Purchase a ticket from the system and return the purchased ticket
    public static Ticket purchaseTicket() {
        if (totalTickets > 0) {
            totalTickets--;
            // Here we create a new ticket (you can customize the ticket creation logic)
            Ticket ticket = new Ticket(totalTickets, "Event Ticket " + totalTickets);
            System.out.println("Ticket purchased: " + ticket);
            return ticket;  // Return the purchased ticket
        } else {
            System.out.println("No tickets available for purchase.");
            return null;  // Return null if no tickets are available
        }
    }

    // Get current ticket count
    public static int getTicketCount() {
        return totalTickets;
    }

    // For real-time ticket monitoring
    public static void logTicketTransaction(String transactionType, int tickets) {
        System.out.println(transactionType + ": " + tickets + " tickets. Remaining tickets: " + totalTickets);
    }
}
