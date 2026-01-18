package com.example.view;

import com.example.dto.CulturalHeritageDetailsDTO;

/**
 * View responsible for displaying detailed information of a cultural heritage item.
 */
public class CulturalHeritageDetailsView {

    /**
     * Displays the details of a cultural heritage item.
     * @param dto the data transfer object containing all details
     */
    public void displayDetails(CulturalHeritageDetailsDTO dto) {
        if (dto == null) {
            System.out.println("Error: No data to display.");
            return;
        }
        System.out.println("=== Cultural Heritage Details ===");
        System.out.println("ID: " + dto.getId());
        System.out.println("Name: " + dto.getName());
        System.out.println("Location: " + dto.getLocation());
        System.out.println("Period: " + dto.getPeriod());
        System.out.println("Description: " + dto.getDescription());
        System.out.println("================================");
    }
}