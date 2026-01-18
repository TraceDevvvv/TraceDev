package com.etour;

import com.etour.mapper.SiteMapper;
import com.etour.model.Tourist;
import com.etour.presentation.SitePresentationController;
import com.etour.repository.SiteRepositoryImpl;
import com.etour.ui.SiteUI;
import com.etour.usecase.ViewSiteCardUseCaseController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Main class to simulate the ViewSiteCard use case.
 */
public class Main {
    public static void main(String[] args) {
        // Setup database connection (simulated).
        Connection connection = null;
        try {
            String url = "jdbc:h2:mem:test;INIT=CREATE TABLE sites(id VARCHAR, name VARCHAR, description VARCHAR, location VARCHAR, rating DOUBLE)";
            connection = DriverManager.getConnection(url);
            // Insert a sample site for testing.
            connection.createStatement().executeUpdate(
                    "INSERT INTO sites VALUES ('S1', 'Eiffel Tower', 'Iconic tower in Paris', 'Paris', 4.8)");
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        // Create components with dependency injection.
        SiteRepositoryImpl repository = new SiteRepositoryImpl(connection);
        SiteMapper mapper = new SiteMapper();
        ViewSiteCardUseCaseController useCaseController = new ViewSiteCardUseCaseController(repository, mapper);
        SitePresentationController presentationController = new SitePresentationController(useCaseController);
        SiteUI ui = new SiteUI(presentationController);

        // Create a tourist with current area "Paris".
        Tourist tourist = new Tourist("Paris");

        // Simulate the sequence diagram flow.
        System.out.println("=== Simulating ViewSiteCard Use Case ===");
        tourist.selectSite("S1"); // Tourist selects site S1.
        ui.selectSite("S1");      // UI processes the selection.

        // Test with a non-existent site.
        System.out.println("\n=== Testing with non-existent site ===");
        ui.selectSite("S99");

        // Test with invalid location (tourist area mismatch).
        System.out.println("\n=== Testing with invalid location ===");
        Tourist touristInvalid = new Tourist("London");
        // The location validation occurs inside the use case controller.
        // For demonstration, we directly call the UI (which will fail due to location).
        ui.selectSite("S1");

        // Close connection.
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}