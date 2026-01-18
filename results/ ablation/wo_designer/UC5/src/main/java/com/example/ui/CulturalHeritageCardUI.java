package com.example.ui;

import com.example.model.CulturalHeritage;
import com.example.service.CulturalHeritageService;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * Console-based UI for Agency Operator to view cultural heritage details.
 * This simulates the user interface for the use case.
 */
public class CulturalHeritageCardUI {
    private CulturalHeritageService service;
    private Scanner scanner;
    private boolean loggedIn;
    
    public CulturalHeritageCardUI() {
        this.service = new CulturalHeritageService();
        this.scanner = new Scanner(System.in);
        this.loggedIn = false;
    }
    
    /**
     * Main method to start the application.
     */
    public static void main(String[] args) {
        CulturalHeritageCardUI ui = new CulturalHeritageCardUI();
        ui.run();
    }
    
    /**
     * Runs the main application loop.
     */
    public void run() {
        System.out.println("=== Cultural Heritage Management System ===");
        
        // Entry Condition: Agency Operator MUST be logged in
        if (!login()) {
            System.out.println("Login failed. Exiting application.");
            return;
        }
        
        boolean running = true;
        while (running) {
            displayMenu();
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    viewCulturalHeritageList();
                    break;
                case "2":
                    viewCulturalHeritageCard();
                    break;
                case "3":
                    System.out.println("Logging out...");
                    loggedIn = false;
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
        System.out.println("Application terminated.");
    }
    
    /**
     * Simulates login process (Entry Condition).
     * @return true if login successful.
     */
    private boolean login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        
        // Simple authentication simulation
        if ("operator".equals(username) && "password".equals(password)) {
            loggedIn = true;
            System.out.println("Login successful! Welcome, Agency Operator.");
            return true;
        } else {
            System.out.println("Invalid credentials.");
            return false;
        }
    }
    
    /**
     * Displays the main menu.
     */
    private void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. View Cultural Heritage List");
        System.out.println("2. View Cultural Heritage Card Details");
        System.out.println("3. Logout");
        System.out.print("Enter your choice: ");
    }
    
    /**
     * Displays list of cultural heritage items (Step 1 of Flow of Events).
     */
    private void viewCulturalHeritageList() {
        System.out.println("\n--- Cultural Heritage List ---");
        List<CulturalHeritage> heritageList = service.getAllCulturalHeritage();
        
        if (heritageList.isEmpty()) {
            System.out.println("No cultural heritage items found.");
            return;
        }
        
        for (CulturalHeritage ch : heritageList) {
            System.out.printf("ID: %d | Title: %s | Type: %s | Location: %s%n",
                    ch.getId(), ch.getTitle(), ch.getType(), ch.getLocation());
        }
        System.out.println("Total items: " + heritageList.size());
    }
    
    /**
     * Handles the complete flow for viewing a cultural heritage card.
     * Steps 2-4 of Flow of Events.
     */
    private void viewCulturalHeritageCard() {
        System.out.println("\n--- View Cultural Heritage Card ---");
        
        // Step 2: Agency Operator selects one cultural good
        System.out.print("Enter the ID of the cultural heritage to view: ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            
            // Step 3: Agency Operator activates function to view the card
            System.out.println("Loading cultural heritage card...");
            
            // Step 4: System loads the data for the selected cultural heritage
            try {
                CulturalHeritage heritage = service.getCulturalHeritageWithTimeout(id);
                
                // Exit Condition: Display details or handle interruption
                if (heritage != null) {
                    displayCulturalHeritageCard(heritage);
                } else {
                    System.out.println("Cultural heritage with ID " + id + " not found.");
                }
            } catch (TimeoutException e) {
                // Exit Condition: Connection to server interrupted
                System.out.println("ERROR: Connection to server ETOUR was interrupted. Please try again later.");
            } catch (ExecutionException e) {
                System.out.println("ERROR: Failed to retrieve cultural heritage data: " + e.getCause().getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("ERROR: Operation was interrupted.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a numeric ID.");
        }
    }
    
    /**
     * Displays detailed card for a cultural heritage item.
     * @param heritage The cultural heritage to display.
     */
    private void displayCulturalHeritageCard(CulturalHeritage heritage) {
        System.out.println("\n=== Cultural Heritage Card ===");
        System.out.println("ID: " + heritage.getId());
        System.out.println("Title: " + heritage.getTitle());
        System.out.println("Type: " + heritage.getType());
        System.out.println("Location: " + heritage.getLocation());
        System.out.println("Historical Period: " + heritage.getHistoricalPeriod());
        System.out.println("Protected Status: " + (heritage.isProtected() ? "Yes" : "No"));
        System.out.println("Image URL: " + heritage.getImageUrl());
        System.out.println("\nDescription:");
        System.out.println(heritage.getDescription());
        System.out.println("==============================");
    }
}