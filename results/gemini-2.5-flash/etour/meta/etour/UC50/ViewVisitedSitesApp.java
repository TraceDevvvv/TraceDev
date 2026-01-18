// ViewVisitedSitesApp.java
package com.etour.visitedsites;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Main application class to simulate the "View Visited Sites" use case.
 * This class handles user interaction, authentication, and displays the list of sites
 * for which a tourist has provided feedback. It also simulates potential server connection
 * interruptions.
 */
public class ViewVisitedSitesApp {

    // A simple in-memory "database" for tourists and sites
    private static Set<Tourist> registeredTourists = new HashSet<>();
    private static Set<Site> availableSites = new HashSet<>();

    // Static initializer to populate some dummy data
    static {
        // Create some sites
        Site eiffelTower = new Site("S001", "Eiffel Tower");
        Site louvreMuseum = new Site("S002", "Louvre Museum");
        Site greatWall = new Site("S003", "Great Wall of China");
        Site colosseum = new Site("S004", "Colosseum");
        Site statueOfLiberty = new Site("S005", "Statue of Liberty");

        availableSites.add(eiffelTower);
        availableSites.add(louvreMuseum);
        availableSites.add(greatWall);
        availableSites.add(colosseum);
        availableSites.add(statueOfLiberty);

        // Create a tourist
        Tourist tourist1 = new Tourist("T001", "john_doe", "hashed_password_john");
        Tourist tourist2 = new Tourist("T002", "jane_smith", "hashed_password_jane");

        // Add some feedback for tourist1
        tourist1.addFeedback(new Feedback("F001", eiffelTower, "T001", 5, "Absolutely breathtaking!"));
        tourist1.addFeedback(new Feedback("F002", louvreMuseum, "T001", 4, "Mona Lisa was smaller than expected."));
        tourist1.addFeedback(new Feedback("F003", greatWall, "T001", 5, "An incredible historical marvel."));

        // Add some feedback for tourist2
        tourist2.addFeedback(new Feedback("F004", colosseum, "T002", 5, "Felt like stepping back in time."));
        tourist2.addFeedback(new Feedback("F005", eiffelTower, "T002", 4, "Beautiful view, but very crowded."));

        registeredTourists.add(tourist1);
        registeredTourists.add(tourist2);
    }

    private Scanner scanner;
    private Tourist authenticatedTourist; // Stores the currently authenticated tourist

    public ViewVisitedSitesApp() {
        scanner = new Scanner(System.in);
    }

    /**
     * Simulates the authentication process for a tourist.
     *
     * @return The authenticated Tourist object, or null if authentication fails.
     */
    private Tourist authenticateTourist() {
        System.out.println("\n--- Tourist Authentication ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine(); // In a real app, this would be hashed

        // Simulate password hashing for comparison
        String hashedPassword = "hashed_password_" + username.toLowerCase().replace(" ", "_");

        for (Tourist tourist : registeredTourists) {
            if (tourist.getUsername().equalsIgnoreCase(username) && tourist.authenticate(hashedPassword)) {
                System.out.println("Authentication successful. Welcome, " + tourist.getUsername() + "!");
                return tourist;
            }
        }
        System.out.println("Authentication failed. Invalid username or password.");
        return null;
    }

    /**
     * Simulates a connection interruption to the ETOUR server.
     * This method randomly throws an ETOURServerException to simulate network issues.
     *
     * @throws ETOURServerException if a simulated connection interruption occurs.
     */
    private void simulateServerConnection() throws ETOURServerException {
        // Simulate a 10% chance of connection interruption
        if (Math.random() < 0.1) {
            throw new ETOURServerException("Connection to ETOUR server interrupted. Please try again later.");
        }
    }

    /**
     * Displays the list of sites for which the authenticated tourist has issued feedback.
     * This method incorporates the "Interruption of the connection to the server ETOUR" quality requirement.
     */
    public void displayVisitedSitesWithFeedback() {
        if (authenticatedTourist == null) {
            System.out.println("Error: No tourist is currently authenticated.");
            return;
        }

        System.out.println("\n--- Displaying Visited Sites with Feedback for " + authenticatedTourist.getUsername() + " ---");

        try {
            // Simulate server connection before fetching data
            simulateServerConnection();

            // Get the set of unique sites visited by the tourist that have feedback
            Set<Site> visitedSites = authenticatedTourist.getVisitedSitesWithFeedback();

            if (visitedSites.isEmpty()) {
                System.out.println("You have not provided feedback for any sites yet.");
            } else {
                System.out.println("Sites you have visited and provided feedback for:");
                int i = 1;
                for (Site site : visitedSites) {
                    System.out.println(i++ + ". " + site.getName() + " (ID: " + site.getSiteId() + ")");
                }
            }
        } catch (ETOURServerException e) {
            System.err.println("System Error: " + e.getMessage());
            System.out.println("Could not retrieve visited sites due to a server connection issue.");
        } catch (Exception e) {
            // Catch any other unexpected errors
            System.err.println("An unexpected error occurred: " + e.getMessage());
            System.out.println("Please contact support if the issue persists.");
        }
    }

    /**
     * Runs the main application loop.
     */
    public void run() {
        System.out.println("Welcome to the ETOUR Visited Sites Viewer!");

        // Entry condition: Tourist has successfully authenticated
        while (authenticatedTourist == null) {
            authenticatedTourist = authenticateTourist();
            if (authenticatedTourist == null) {
                System.out.println("Authentication failed. Please try again.");
                // Optional: add a delay or limit retry attempts
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Application interrupted during authentication retry.");
                    return;
                }
            }
        }

        boolean running = true;
        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. View My Visited Sites with Feedback");
            System.out.println("2. Logout");
            System.out.println("3. Exit Application");
            System.out.print("Please choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        // Flow of events 1: Select the feature to display the list of sites visited personal.
                        // Flow of events 2: Upload the list of sites for which the tourist has issued a Feedback
                        displayVisitedSitesWithFeedback();
                        // Exit condition: The system displays a list of sites visited.
                        break;
                    case 2:
                        authenticatedTourist = null;
                        System.out.println("Logged out successfully. Please authenticate again to continue.");
                        // Re-authenticate for the next actions
                        while (authenticatedTourist == null) {
                            authenticatedTourist = authenticateTourist();
                            if (authenticatedTourist == null) {
                                System.out.println("Authentication failed. Please try again.");
                                try {
                                    TimeUnit.SECONDS.sleep(1);
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                    System.err.println("Application interrupted during authentication retry.");
                                    return;
                                }
                            }
                        }
                        break;
                    case 3:
                        running = false;
                        System.out.println("Exiting application. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        scanner.close();
    }

    /**
     * Custom exception for simulating ETOUR server connection issues.
     */
    static class ETOURServerException extends Exception {
        public ETOURServerException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        ViewVisitedSitesApp app = new ViewVisitedSitesApp();
        app.run();
    }
}