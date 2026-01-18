package com.example.tourist;

import java.util.List;
import java.util.ArrayList;

/**
 * Entity class representing a Tourist.
 * Corresponds to the Tourist class in the class diagram.
 */
public class Tourist {
    private String id;
    private String name;
    private String contactInfo;
    private List<String> bookingHistory;

    public Tourist(String id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.bookingHistory = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public List<String> getBookingHistory() {
        return bookingHistory;
    }

    public void addBooking(String bookingId) {
        this.bookingHistory.add(bookingId);
    }
}