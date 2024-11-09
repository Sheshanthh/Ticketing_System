package com.springcrud.eventticketingsystem.cli;

import com.springcrud.eventticketingsystem.model.Ticket;
import com.springcrud.eventticketingsystem.service.TicketService;

import java.util.concurrent.LinkedBlockingQueue;

public class TicketPool {

    private static int maxTicketCapacity = 0;  // Default to 0 until set by vendor
    private static LinkedBlockingQueue<Ticket> ticketQueue = new LinkedBlockingQueue<>();

    // Method to set the maximum ticket capacity
    public static void setMaxTicketCapacity(int capacity) {
        if (capacity > 0) {
            maxTicketCapacity = capacity;
            System.out.println("Max ticket capacity set to: " + maxTicketCapacity);
        } else {
            System.out.println("Invalid capacity. Please set a positive number.");
        }
    }

    // Method to add tickets
    public static void addTickets(int numTickets) {
        // Only add tickets if the total does not exceed the max capacity
        if (maxTicketCapacity == 0) {
            System.out.println("Please set the max ticket capacity first.");
            return;
        }

        if (ticketQueue.size() + numTickets <= maxTicketCapacity) {
            for (int i = 0; i < numTickets; i++) {
                Ticket newTicket = new Ticket(ticketQueue.size() + 1, "Event Ticket " + (ticketQueue.size() + 1));
                ticketQueue.offer(newTicket); // Add ticket to queue
            }
            System.out.println(numTickets + " tickets added. Total tickets: " + ticketQueue.size());
        } else {
            int ticketsToAdd = maxTicketCapacity - ticketQueue.size();
            if (ticketsToAdd > 0) {
                for (int i = 0; i < ticketsToAdd; i++) {
                    Ticket newTicket = new Ticket(ticketQueue.size() + 1, "Event Ticket " + (ticketQueue.size() + 1));
                    ticketQueue.offer(newTicket); // Add ticket to queue
                }
                System.out.println(ticketsToAdd + " tickets added. Total tickets: " + ticketQueue.size());
            } else {
                System.out.println("Maximum ticket capacity reached. No more tickets can be added.");
            }
        }
    }

    // Method to purchase a ticket
    public static Ticket purchaseTicket() {
        Ticket ticket = ticketQueue.poll(); // Remove and return the first ticket in the queue
        if (ticket != null) {
            System.out.println("Ticket purchased: " + ticket);
        } else {
            System.out.println("No tickets available.");
        }
        return ticket;
    }

    // Method to get the current ticket count
    public static int getTicketCount() {
        return ticketQueue.size(); // Return the current size of the ticket queue
    }
}
