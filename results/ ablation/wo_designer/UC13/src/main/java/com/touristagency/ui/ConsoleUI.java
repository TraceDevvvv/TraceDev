package com.touristagency.ui;

import com.touristagency.model.Tourist;
import com.touristagency.repository.TouristRepository;
import com.touristagency.service.TouristAccountService;

import java.util.List;
import java.util.Scanner;

/**
 * Simple console interface for the Agency Operator.
 */
public class ConsoleUI {
    private final TouristAccountService accountService;
    private final Scanner scanner;

    public ConsoleUI(TouristAccountService accountService) {
        this.accountService = accountService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=== Tourist Account Management ===");
        System.out.println("Logged in as: Agency Operator");

        while (true) {
            displayMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    listAllTourists();
                    break;
                case "2":
                    toggleAccount();
                    break;
                case "3":
                    checkStatus();
                    break;
                case "4":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. List all tourists");
        System.out.println("2. Enable/Disable tourist account");
        System.out.println("3. Check account status");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private void listAllTourists() {
        TouristRepository repo = new TouristRepository();
        List<Tourist> tourists = repo.findAll();
        if (tourists.isEmpty()) {
            System.out.println("No tourists found.");
            return;
        }
        System.out.println("\nList of Tourists:");
        for (Tourist t : tourists) {
            System.out.println("ID: " + t.getId() + ", Name: " + t.getName() +
                    ", Email: " + t.getEmail() + ", Enabled: " + t.isAccountEnabled());
        }
    }

    private void toggleAccount() {
        System.out.println("\n--- Enable/Disable Tourist Account ---");
        System.out.print("Enter Tourist ID: ");
        String touristId = scanner.nextLine().trim();

        // Show current status
        String status = accountService.getAccountStatus(touristId);
        System.out.println("Current: " + status);

        // Ask for confirmation (simulating step 2 and 3 of Flow of Events)
        System.out.print("Confirm activation/deactivation? (yes/no): ");
        String confirmation = scanner.nextLine().trim();
        boolean confirmed = confirmation.equalsIgnoreCase("yes");

        // Perform the operation
        String result = accountService.toggleAccountStatus(touristId, confirmed);
        System.out.println(result);
    }

    private void checkStatus() {
        System.out.println("\n--- Check Account Status ---");
        System.out.print("Enter Tourist ID: ");
        String touristId = scanner.nextLine().trim();
        String status = accountService.getAccountStatus(touristId);
        System.out.println(status);
    }
}