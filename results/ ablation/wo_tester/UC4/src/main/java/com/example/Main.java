package com.example;

import com.example.controller.CulturalObjectController;
import com.example.domain.DateRange;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Main class to simulate the runnable application.
 * This class demonstrates the sequence of interactions as per the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Cultural Object Search ===");

        CulturalObjectController controller = new CulturalObjectController();

        // Check if search is accessible (Entry Condition)
        if (!CulturalObjectController.isSearchAccessible()) {
            System.out.println("Search functionality is not accessible.");
            return;
        }

        // 1. Activate Search (Flow of Events 1)
        controller.activateSearch();

        // 2. Get Search Form (Flow of Events 2)
        controller.getSearchForm();

        // 3. Simulate user filling and submitting the form (Flow of Events 3-4)
        Map<String, Object> formData = new HashMap<>();
        formData.put("criteria", "Ancient");
        formData.put("filterType", "Classical");
        DateRange dateRange = new DateRange();
        dateRange.setStartDate(new Date(System.currentTimeMillis() - 1000000000L));
        dateRange.setEndDate(new Date());
        formData.put("dateRange", dateRange);

        controller.fillAndSubmitSearch(formData);

        System.out.println("=== Search Completed ===");
    }
}