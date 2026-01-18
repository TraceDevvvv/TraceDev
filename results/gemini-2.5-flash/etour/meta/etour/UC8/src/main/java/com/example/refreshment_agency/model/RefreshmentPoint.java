package com.example.refreshment_agency.model;

/**
 * Model class representing a refreshment point.
 * This class holds the data pertinent to a point of rest, including its ID, name, address,
 * contact information, capacity, serv offered, and operational status.
 */
public class RefreshmentPoint {
    private long id;
    private String name;
    private String address;
    private String contactInfo;
    private int capacity;
    private String servOffered;
    private Status status;

    /**
     * Enum to represent the operational status of a refreshment point.
     */
    public enum Status {
        ACTIVE,
        INACTIVE
    }

    /**
     * Constructs a new RefreshmentPoint with the specified details.
     *
     * @param id              The unique identifier for the refreshment point.
     * @param name            The name of the refreshment point.
     * @param address         The physical address of the refreshment point.
     * @param contactInfo     Contact details for the refreshment point (e.g., phone, email).
     * @param capacity        The maximum capacity of the refreshment point.
     * @param servOffered A description of serv available (e.g., "Coffee, Snacks, Wi-Fi").
     * @param status          The operational status of the refreshment point (ACTIVE or INACTIVE).
     */
    public RefreshmentPoint(long id, String name, String address, String contactInfo, int capacity, String servOffered, Status status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactInfo = contactInfo;
        this.capacity = capacity;
        this.servOffered = servOffered;
        this.status = status;
    }

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getServOffered() {
        return servOffered;
    }

    public void setServOffered(String servOffered) {
        this.servOffered = servOffered;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RefreshmentPoint{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", address='" + address + '\'' +
               ", contactInfo='" + contactInfo + '\'' +
               ", capacity=" + capacity +
               ", servOffered='" + servOffered + '\'' +
               ", status=" + status +
               '}';
    }
}