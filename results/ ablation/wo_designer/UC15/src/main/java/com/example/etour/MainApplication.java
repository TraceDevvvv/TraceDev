package com.example.etour;

import com.example.etour.model.Tourist;
import com.example.etour.service.TouristService;
import com.example.etour.ui.ModifyTouristUI;

import java.time.LocalDate;

/**
 * Main class to run the application.
 */
public class MainApplication {
    public static void main(String[] args) {
        // Initialize service with sample data
        TouristService service = new TouristService();
        populateSampleData(service);

        // Create and run UI
        ModifyTouristUI ui = new ModifyTouristUI(service);
        ui.run();
    }

    private static void populateSampleData(TouristService service) {
        Tourist t1 = new Tourist("T001", "John", "Doe", "john.doe@example.com",
                "+123456789", LocalDate.of(1985, 5, 15), "USA", "P1234567");
        Tourist t2 = new Tourist("T002", "Jane", "Smith", "jane.smith@example.com",
                "+987654321", LocalDate.of(1990, 8, 22), "UK", "P7654321");
        Tourist t3 = new Tourist("T003", "Robert", "Johnson", "robert.j@example.com",
                "+555123456", LocalDate.of(1978, 12, 5), "Canada", "P8889999");

        service.addTourist(t1);
        service.addTourist(t2);
        service.addTourist(t3);
    }
}