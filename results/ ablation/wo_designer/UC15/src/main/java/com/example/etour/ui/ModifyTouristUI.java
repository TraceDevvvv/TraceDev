package com.example.etour.ui;

import com.example.etour.model.Tourist;
import com.example.etour.service.TouristService;
import com.example.etour.exceptions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Console-based UI for the Agency Operator to modify tourist data.
 * Implements the flow described in the use case.
 */
public class ModifyTouristUI {
    private TouristService touristService;
    private Scanner scanner;
    private boolean loggedIn;

    public ModifyTouristUI(TouristService touristService) {
        this.touristService = touristService;
        this.scanner = new Scanner(System.in);
        this.loggedIn = false; // Initially not logged in
    }

    /**
     * Simulates Agency Operator login.
     */
    public void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        // Simple authentication simulation
        if ("operator".equals(username) && "password".equals(password)) {
            loggedIn = true;
            System.out.println("Login successful. Welcome, Agency Operator.");
        } else {
            System.out.println("Login failed.");
            System.exit(0);
        }
    }

    /**
     * Main method that runs the use case flow.
     */
    public void run() {
        // Entry Condition: Agency Operator IS logged in.
        if (!loggedIn) {
            login();
        }

        try {
            // Step 1: Agency Operator activates the use case SearchTourist.
            System.out.println("\n--- Search Tourists ---");
            System.out.print("Enter search query (or press Enter to list all): ");
            String query = scanner.nextLine();

            // Step 2: Agency Operator obtains a list of Tourists.
            List<Tourist> tourists = touristService.searchTourists(query);
            if (tourists.isEmpty()) {
                System.out.println("No tourists found.");
                return;
            }

            // Display list
            System.out.println("\nFound " + tourists.size() + " tourist(s):");
            for (int i = 0; i < tourists.size(); i++) {
                Tourist t = tourists.get(i);
                System.out.println((i + 1) + ". " + t.getFirstName() + " " + t.getLastName() +
                        " (ID: " + t.getId() + ", Email: " + t.getEmail() + ")");
            }

            // Step 3: Agency Operator selects one Tourist from the list.
            System.out.print("\nSelect tourist by number: ");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > tourists.size()) {
                System.out.println("Invalid selection.");
                return;
            }
            Tourist selectedTourist = tourists.get(choice - 1);
            String touristId = selectedTourist.getId();

            // Step 4: Agency Operator activates a function to modify data.
            // Step 5: System loads the data of the selected Tourist.
            Tourist touristToEdit = touristService.getTouristById(touristId);

            // Step 6: System displays the data in a form allowing change.
            System.out.println("\n--- Edit Tourist Data ---");
            System.out.println("Current data:");
            displayTourist(touristToEdit);

            // Step 7: Agency Operator edits the fields in the form.
            Tourist updatedTourist = editTouristForm(touristToEdit);

            // Step 8: Agency Operator submits the form.
            // Step 9: System verifies the information (done in service).
            // Step 10: System asks for confirmation of the change.
            System.out.println("\nUpdated data:");
            displayTourist(updatedTourist);
            System.out.print("\nConfirm changes? (yes/no): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            if (!confirm.equals("yes")) {
                System.out.println("Modification cancelled.");
                return;
            }

            // Step 11: Agency Operator confirms the operation.
            // Step 12: System stores the modified data for the selected account.
            touristService.updateTourist(touristId, updatedTourist);
            System.out.println("Tourist data updated successfully.");

        } catch (TouristNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (ValidationException e) {
            System.out.println("Validation Error: " + e.getMessage());
            // According to quality requirement, activate use case Errored (simulated here)
            System.out.println("[Use Case Errored activated due to invalid data.]");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: please enter a number.");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            // Simulate connection interruption
            if (e.getMessage() != null && e.getMessage().contains("connection")) {
                System.out.println("[Exit Condition: Connection to server ETOUR interrupted.]");
            }
        }
    }

    /**
     * Displays tourist details.
     */
    private void displayTourist(Tourist tourist) {
        System.out.println("ID: " + tourist.getId());
        System.out.println("First Name: " + tourist.getFirstName());
        System.out.println("Last Name: " + tourist.getLastName());
        System.out.println("Email: " + tourist.getEmail());
        System.out.println("Phone: " + tourist.getPhoneNumber());
        System.out.println("Date of Birth: " +
                (tourist.getDateOfBirth() != null ? tourist.getDateOfBirth().format(DateTimeFormatter.ISO_LOCAL_DATE) : "N/A"));
        System.out.println("Nationality: " + tourist.getNationality());
        System.out.println("Passport: " + tourist.getPassportNumber());
    }

    /**
     * Interactive form to edit tourist fields.
     * @param original the original tourist data
     * @return a new Tourist object with updated fields
     */
    private Tourist editTouristForm(Tourist original) {
        Tourist updated = new Tourist();
        // Copy original values
        updated.setId(original.getId());
        updated.setFirstName(original.getFirstName());
        updated.setLastName(original.getLastName());
        updated.setEmail(original.getEmail());
        updated.setPhoneNumber(original.getPhoneNumber());
        updated.setDateOfBirth(original.getDateOfBirth());
        updated.setNationality(original.getNationality());
        updated.setPassportNumber(original.getPassportNumber());

        System.out.println("\nEnter new values (press Enter to keep current):");

        System.out.print("First Name [" + original.getFirstName() + "]: ");
        String input = scanner.nextLine().trim();
        if (!input.isEmpty()) updated.setFirstName(input);

        System.out.print("Last Name [" + original.getLastName() + "]: ");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) updated.setLastName(input);

        System.out.print("Email [" + original.getEmail() + "]: ");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) updated.setEmail(input);

        System.out.print("Phone Number [" + original.getPhoneNumber() + "]: ");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) updated.setPhoneNumber(input);

        System.out.print("Date of Birth (YYYY-MM-DD) [" +
                (original.getDateOfBirth() != null ? original.getDateOfBirth().format(DateTimeFormatter.ISO_LOCAL_DATE) : "") + "]: ");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) {
            try {
                updated.setDateOfBirth(LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format; keeping original.");
            }
        }

        System.out.print("Nationality [" + original.getNationality() + "]: ");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) updated.setNationality(input);

        System.out.print("Passport Number [" + original.getPassportNumber() + "]: ");
        input = scanner.nextLine().trim();
        if (!input.isEmpty()) updated.setPassportNumber(input);

        return updated;
    }
}