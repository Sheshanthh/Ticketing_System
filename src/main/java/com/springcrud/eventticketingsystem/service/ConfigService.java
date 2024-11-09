package com.springcrud.eventticketingsystem.service;

import java.util.Scanner;

public class ConfigService {

    private static int totalTickets = 0;
    private static int ticketReleaseRate = 2;  // Default ticket release rate
    private static int customerRetrievalRate = 1;  // Default customer retrieval rate
    private static int maxTicketCapacity = 100;  // Default max capacity

    public static void configure() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter total number of tickets available:");
        totalTickets = scanner.nextInt();

        System.out.println("Enter ticket release rate (in seconds):");
        ticketReleaseRate = scanner.nextInt();

        System.out.println("Enter customer retrieval rate (in seconds):");
        customerRetrievalRate = scanner.nextInt();

        System.out.println("Enter max ticket capacity:");
        maxTicketCapacity = scanner.nextInt();

        System.out.println("\nSystem configured with:");
        System.out.println("Total Tickets: " + totalTickets);
        System.out.println("Ticket Release Rate: " + ticketReleaseRate + " seconds");
        System.out.println("Customer Retrieval Rate: " + customerRetrievalRate + " seconds");
        System.out.println("Max Ticket Capacity: " + maxTicketCapacity + "\n");

        scanner.close();
    }

    // Getters for ticket configuration
    public static int getTotalTickets() {
        return totalTickets;
    }

    public static int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public static int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public static int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    // Method to update total tickets available dynamically
    public static void updateTotalTickets(int tickets) {
        totalTickets += tickets;
    }

    // Method to check if tickets are available for sale
    public static boolean hasTickets() {
        return totalTickets > 0;
    }
}
