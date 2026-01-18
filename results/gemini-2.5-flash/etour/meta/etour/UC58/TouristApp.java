package com.etour.app;

import com.etour.data.DatabaseService;
import com.etour.exceptions.ETOURConnectionException;
import com.etour.site.Site;
import com.etour.ui.SiteCardViewer;

import java.util.Scanner;

/**
 * The main application class for the ETOUR system, simulating a tourist's interaction.
 * This class orchestrates the flow of events for viewing a site card,
 * including user input, data retrieval, and display.
 */
public class TouristApp {

    private final DatabaseService databaseService;
    private final SiteCardViewer siteCardViewer;
    private final Scanner scanner;

    /**
     * Constructor for TouristApp.
     * Initializes the database service, site card viewer, and scanner for user input.
     */
    public TouristApp() {
        this.databaseService = new DatabaseService();
        this.siteCardViewer = new SiteCardViewer();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Simulates the authentication process.
     * In a real application, this would involve checking credentials.
     * For this simulation, it always returns true.
     *
     * @return true if authentication is successful, false otherwise.
     */
    private boolean authenticateTourist() {
        System.out.println("Simulating tourist authentication...");
        // In a real scenario, this would involve user login.
        // For this use case, we assume successful authentication.
        return true;
    }

    /**
     * Simulates the tourist selecting a site from a list (e.g., search results, favorites).
     * Prompts the user to enter a site ID.
     *
     * @return The site ID entered by the user.
     */
    private String selectSite() {
        System.out.println("\n--- Select a Site ---");
        System.out.println("Available Site IDs: S001 (Eiffel Tower), S002 (Colosseum), S003 (Great Wall), S004 (Machu Picchu), S005 (Pyramids of Giza)");
        System.out.print("Please enter the Site ID you wish to view: ");
        return scanner.nextLine();
    }

    /**
     * Displays the details of a selected site.
     * This method encapsulates the core logic of the "ViewSiteCard" use case.
     *
     * @param siteId The ID of the site to display.
     */
    public void viewSiteCard(String siteId) {
        System.out.println("\nAttempting to view details for Site ID: " + siteId);
        try {
            // 2. Upload data from the database.
            Site site = databaseService.getSiteDetails(siteId);

            // Exit conditions: The system displays the details of the selected site.
            if (site != null) {
                siteCardViewer.displaySiteDetails(site);
            } else {
                System.out.println("Could not find details for Site ID: " + siteId + ". Please check the ID and try again.");
            }
        } catch (ETOURConnectionException e) {
            // Interruption of the connection to the server ETOUR.
            System.err.println("Error: " + e.getMessage());
            System.err.println("Please check your network connection and try again.");
        } catch (IllegalArgumentException e) {
            System.err.println("Input Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * The main entry point for the TouristApp simulation.
     * Handles the overall flow of the application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        TouristApp app = new TouristApp();

        // Entry conditions: The Tourist has successfully authenticated to the system.
        if (app.authenticateTourist()) {
            System.out.println("Tourist successfully authenticated.");

            // Simulate being in one of the specified areas (Research Results, List of Sites, etc.)
            // and then selecting a function.
            // 1. Select the function for displaying the card on a site chosen.
            String selectedSiteId = app.selectSite();

            // Execute the core use case logic
            app.viewSiteCard(selectedSiteId);
        } else {
            System.out.println("Authentication failed. Exiting application.");
        }

        app.scanner.close(); // Close the scanner to release system resources.
    }
}