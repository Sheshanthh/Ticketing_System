package com.springcrud.eventticketingsystem.cli;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class TicketConsumer {

    private static final String BASE_URL = "http://localhost:8080/api/tickets/add";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Starting ticket handling...");

        // CLI loop for user input
        while (true) {
            System.out.println("\nEnter number of tickets to add (or type 'exit' to quit): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting ticket handling...");
                break;
            }

            try {
                int numTickets = Integer.parseInt(input);
                if (numTickets > 0) {
                    // Call backend API to add tickets
                    addTicketsToBackend(numTickets);
                } else {
                    System.out.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        scanner.close();
    }

    // Method to send an HTTP request to the backend to add tickets
    private static void addTicketsToBackend(int numTickets) {
        try {
            // Construct the HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "?numTickets=" + numTickets))
                    .POST(HttpRequest.BodyPublishers.noBody()) // Use POST request with no body
                    .build();

            // Send the request using HttpClient
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the response from the backend
            if (response.statusCode() == 200) {
                System.out.println(response.body());
            } else {
                System.out.println("Failed to add tickets. Status Code: " + response.statusCode());
            }

        } catch (Exception e) {
            System.out.println("Error while sending the request: " + e.getMessage());
        }
    }
}
