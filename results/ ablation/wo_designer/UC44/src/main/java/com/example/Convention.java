package com.example;

import java.util.Date;

/**
 * Represents a Convention with its details.
 */
public class Convention {
    private String restaurantName;
    private String operatorName;
    private String agencyName;
    private Date startDate;
    private Date endDate;
    private int numberOfGuests;
    private String specialRequirements;

    // Constructor
    public Convention(String restaurantName, String operatorName, String agencyName,
                      Date startDate, Date endDate, int numberOfGuests, String specialRequirements) {
        this.restaurantName = restaurantName;
        this.operatorName = operatorName;
        this.agencyName = agencyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfGuests = numberOfGuests;
        this.specialRequirements = specialRequirements;
    }

    // Getters and Setters
    public String getRestaurantName() { return restaurantName; }
    public void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName; }

    public String getOperatorName() { return operatorName; }
    public void setOperatorName(String operatorName) { this.operatorName = operatorName; }

    public String getAgencyName() { return agencyName; }
    public void setAgencyName(String agencyName) { this.agencyName = agencyName; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public int getNumberOfGuests() { return numberOfGuests; }
    public void setNumberOfGuests(int numberOfGuests) { this.numberOfGuests = numberOfGuests; }

    public String getSpecialRequirements() { return specialRequirements; }
    public void setSpecialRequirements(String specialRequirements) { this.specialRequirements = specialRequirements; }

    @Override
    public String toString() {
        return "Convention Details:\n" +
               "Restaurant: " + restaurantName + "\n" +
               "Operator: " + operatorName + "\n" +
               "Agency: " + agencyName + "\n" +
               "Start Date: " + startDate + "\n" +
               "End Date: " + endDate + "\n" +
               "Number of Guests: " + numberOfGuests + "\n" +
               "Special Requirements: " + specialRequirements;
    }
}