package com.springcrud.eventticketingsystem.cli;

import com.springcrud.eventticketingsystem.service.ConfigService;
import com.springcrud.eventticketingsystem.service.TicketService;

public class TicketCLI {

    private static boolean isRunning = false;

    public static void startTicketHandling() {
        if (!isRunning) {
            isRunning = true;
            System.out.println("Starting ticket handling...");
            // Start the vendor and customer threads
            startVendors();
            startCustomers();
        } else {
            System.out.println("Ticket handling is already running.");
        }
    }

    public static void stopTicketHandling() {
        if (isRunning) {
            isRunning = false;
            System.out.println("Stopping ticket handling...");
            // Stop all operations gracefully
        } else {
            System.out.println("Ticket handling is not running.");
        }
    }

    private static void startVendors() {
        // Vendors release tickets at the configured rate
        Runnable vendorTask = new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    if (ConfigService.getTotalTickets() < ConfigService.getMaxTicketCapacity()) {
                        TicketService.addTickets(1); // Adding 1 ticket at a time
                        TicketService.logTicketTransaction("Vendor added", 1); // Log the transaction
                    } else {
                        System.out.println("Max ticket capacity reached. Vendors will stop adding tickets.");
                        break; // Stop adding tickets if max capacity is reached
                    }
                    try {
                        Thread.sleep(ConfigService.getTicketReleaseRate() * 1000); // Sleep for the configured rate
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(vendorTask).start();
    }

    private static void startCustomers() {
        // Customers purchase tickets at the configured rate
        Runnable customerTask = new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    if (ConfigService.hasTickets()) {
                        TicketService.purchaseTicket(); // Purchasing a ticket
                        TicketService.logTicketTransaction("Customer purchased", 1); // Log the transaction
                    } else {
                        System.out.println("No tickets available for purchase.");
                        break; // Stop if no tickets are available
                    }
                    try {
                        Thread.sleep(ConfigService.getCustomerRetrievalRate() * 1000); // Sleep for the configured rate
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        new Thread(customerTask).start();
    }

    public static void main(String[] args) {
        // First, configure the system with dynamic inputs
        ConfigService.configure();

        // Handle commands
        startTicketHandling(); // You can also call stopTicketHandling() as needed
    }
}
