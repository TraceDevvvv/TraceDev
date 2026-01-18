package com.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a physical address which can have multiple teachings associated with it.
 */
public class Address {
    private String id;
    private String name;
    private String locationDetails;
    private List<Teaching> teachings; // List of teachings associated with this address

    /**
     * Constructs a new Address instance.
     *
     * @param id              Unique identifier for the address.
     * @param name            Name of the address (e.g., "Main Office").
     * @param locationDetails Detailed location information.
     */
    public Address(String id, String name, String locationDetails) {
        this.id = id;
        this.name = name;
        this.locationDetails = locationDetails;
        this.teachings = new ArrayList<>(); // Initialize with an empty list
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocationDetails() {
        return locationDetails;
    }

    public void setLocationDetails(String locationDetails) {
        this.locationDetails = locationDetails;
    }

    /**
     * Returns an unmodifiable list of teachings associated with this address.
     * This prevents external modification of the internal list.
     *
     * @return A list of teachings.
     */
    public List<Teaching> getTeachings() {
        return Collections.unmodifiableList(teachings);
    }

    /**
     * Sets the list of teachings for this address.
     * Creates a new ArrayList to ensure internal list is not directly modified from outside.
     *
     * @param teachings The new list of teachings to associate.
     */
    public void setTeachings(List<Teaching> teachings) {
        // Ensure the internal list is a copy to prevent direct external modification
        this.teachings = new ArrayList<>(teachings);
    }

    /**
     * Adds a single teaching to the address's list of teachings, if not already present.
     *
     * @param teaching The teaching to add.
     */
    public void addTeaching(Teaching teaching) {
        if (teaching != null && !this.teachings.contains(teaching)) {
            this.teachings.add(teaching);
        }
    }

    /**
     * Removes a teaching from the address's list based on its ID.
     *
     * @param teachingId The ID of the teaching to remove.
     */
    public void removeTeaching(String teachingId) {
        this.teachings.removeIf(t -> t.getId().equals(teachingId));
    }

    /**
     * Retrieves a specific teaching from the address's list by its ID.
     *
     * @param teachingId The ID of the teaching to retrieve.
     * @return The Teaching object if found, otherwise null.
     */
    public Teaching getTeaching(String teachingId) {
        return this.teachings.stream()
                .filter(t -> t.getId().equals(teachingId))
                .findFirst()
                .orElse(null);
    }

    /**
     * Updates the list of teachings associated with this address.
     * This method replaces the existing teachings with the new list provided.
     *
     * @param newTeachings The new list of teachings to associate with the address.
     */
    public void updateTeachings(List<Teaching> newTeachings) {
        // Clear existing teachings and add all from the new list
        this.teachings.clear();
        if (newTeachings != null) {
            this.teachings.addAll(newTeachings);
        }
        System.out.println("Address '" + this.name + "' teachings updated to: " +
                           this.teachings.stream().map(Teaching::getTitle).collect(Collectors.joining(", ")));
    }

    @Override
    public String toString() {
        return "Address{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", locationDetails='" + locationDetails + '\'' +
               ", teachings=" + teachings.stream().map(Teaching::getTitle).collect(Collectors.joining(", ")) +
               '}';
    }
}