package com.springcrud.eventticketingsystem.model;

public class Ticket {

    private int id;  // Add the id property
    private String name;  // Add the name property

    // Constructor: Takes both ID and name
    public Ticket(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Ticket(int id) {
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Ticket{id=" + id + ", name='" + name + "'}";
    }
}
