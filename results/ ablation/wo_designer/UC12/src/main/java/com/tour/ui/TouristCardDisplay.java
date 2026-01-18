package com.tour.ui;

import com.tour.models.Tourist;

/**
 * UI component responsible for displaying the Tourist card.
 */
public class TouristCardDisplay {

    /**
     * Displays detailed information of a Tourist in a formatted card.
     */
    public void displayTouristCard(Tourist tourist) {
        if (tourist == null) {
            System.out.println("No tourist data available to display.");
            return;
        }

        System.out.println("\n======= TOURIST CARD =======");
        System.out.println("ID: " + tourist.getId());
        System.out.println("Name: " + tourist.getName());
        System.out.println("Email: " + tourist.getEmail());
        System.out.println("Phone: " + tourist.getPhone());
        System.out.println("Nationality: " + tourist.getNationality());
        System.out.println("Passport Number: " + tourist.getPassportNumber());
        System.out.println("===========================\n");
    }
}