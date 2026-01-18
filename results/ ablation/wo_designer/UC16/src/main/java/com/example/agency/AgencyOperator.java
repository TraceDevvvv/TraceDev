package com.example.agency;

import com.example.tourist.Tourist;
import com.example.tourist.TouristRepository;
import com.example.etour.ETourServerConnection;
import java.util.List;
import java.util.Scanner;

public class AgencyOperator {
    private TouristRepository touristRepository;
    private ETourServerConnection serverConnection;
    private Scanner scanner;
    private boolean loggedIn;

    public AgencyOperator(TouristRepository touristRepository, ETourServerConnection serverConnection) {
        this.touristRepository = touristRepository;
        this.serverConnection = serverConnection;
        this.scanner = new Scanner(System.in);
        this.loggedIn = false;
    }

    public void login() {
        // Simple login simulation
        System.out.print("Enter operator username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        // For demonstration, accept any non-empty credentials
        if (!username.isEmpty() && !password.isEmpty()) {
            loggedIn = true;
            System.out.println("Login successful.");
            serverConnection.connect();
        } else {
            System.out.println("Login failed.");
        }
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void logout() {
        loggedIn = false;
        serverConnection.disconnect();
        System.out.println("Logged out.");
    }

    public void deleteTouristAccount() {
        if (!loggedIn) {
            System.out.println("Error: Agency Operator must be logged in.");
            return;
        }

        // Step 1: Obtain tourist list by search
        System.out.print("Enter search query (or press Enter to list all tourists): ");
        String query = scanner.nextLine();
        List<Tourist> tourists = touristRepository.searchTourists(query);
        
        if (tourists.isEmpty()) {
            System.out.println("No tourists found.");
            return;
        }

        // Display list
        System.out.println("Tourist List:");
        for (int i = 0; i < tourists.size(); i++) {
            System.out.println((i + 1) + ". " + tourists.get(i));
        }

        // Step 2: Select one Tourist
        System.out.print("Select tourist number to delete: ");
        int selection;
        try {
            selection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        if (selection < 1 || selection > tourists.size()) {
            System.out.println("Invalid selection.");
            return;
        }

        Tourist selectedTourist = tourists.get(selection - 1);

        // Step 3: Activation of disposal feature (implicitly done by this method)
        // Step 4: Ask for confirmation
        System.out.println("You have selected: " + selectedTourist);
        System.out.print("Confirm deletion (yes/no): ");
        String confirmation = scanner.nextLine();

        // Step 5: Agency Operator confirms
        if (!confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Deletion cancelled.");
            return;
        }

        // Step 6: System deletes the selected Tourist data
        boolean deleted = touristRepository.deleteTourist(selectedTourist.getId());
        if (deleted) {
            // Step 7: Notify Agency Operator of elimination
            System.out.println("Tourist account " + selectedTourist.getId() + " has been deleted.");
        } else {
            System.out.println("Failed to delete tourist account.");
        }

        // Step 8: Interrupt connection to server ETOUR
        serverConnection.disconnect();
        // Reconnect for further operations
        serverConnection.connect();
    }

    public void run() {
        System.out.println("=== Tourist Account Deletion System ===");
        login();
        if (loggedIn) {
            deleteTouristAccount();
            logout();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        TouristRepository repository = new TouristRepository();
        ETourServerConnection connection = new ETourServerConnection();
        AgencyOperator operator = new AgencyOperator(repository, connection);
        operator.run();
    }
}