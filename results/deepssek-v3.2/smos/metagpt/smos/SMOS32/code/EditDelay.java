import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Complete Java program for the EditDelay use case.
 * 
 * This program simulates an administrator editing delays for specific dates.
 * It includes login validation, date selection, delay editing, save functionality,
 * and server communication simulation. It handles edge cases like connection
 * interruptions and operation cancellation.
 * 
 * @author Administrator
 * @version 1.0
 */
public class EditDelay {
    
    /**
     * Represents a delay entry for a specific date.
     * Contains the date and delay in minutes.
     */
    static class DelayEntry {
        private LocalDate date;
        private int delayMinutes;
        
        public DelayEntry(LocalDate date, int delayMinutes) {
            this.date = date;
            this.delayMinutes = delayMinutes;
        }
        
        public LocalDate getDate() {
            return date;
        }
        
        public int getDelayMinutes() {
            return delayMinutes;
        }
        
        public void setDelayMinutes(int delayMinutes) {
            this.delayMinutes = delayMinutes;
        }
        
        @Override
        public String toString() {
            return "Date: " + date.format(DateTimeFormatter.ISO_LOCAL_DATE) + 
                   ", Delay: " + delayMinutes + " minutes";
        }
    }
    
    /**
     * Represents an administrator user with login credentials.
     */
    static class Administrator {
        private String username;
        private String password;
        private boolean loggedIn;
        
        public Administrator(String username, String password) {
            this.username = username;
            this.password = password;
            this.loggedIn = false;
        }
        
        public boolean login(String enteredUsername, String enteredPassword) {
            if (username.equals(enteredUsername) && password.equals(enteredPassword)) {
                loggedIn = true;
                return true;
            }
            return false;
        }
        
        public void logout() {
            loggedIn = false;
        }
        
        public boolean isLoggedIn() {
            return loggedIn;
        }
    }
    
    /**
     * Simulates server communication for updating delays.
     */
    static class SmosServer {
        private boolean connectionActive;
        private Map<LocalDate, Integer> serverDelays;
        
        public SmosServer() {
            this.connectionActive = true;
            this.serverDelays = new HashMap<>();
            // Initialize with some sample data
            serverDelays.put(LocalDate.now(), 30);
            serverDelays.put(LocalDate.now().plusDays(1), 45);
        }
        
        /**
         * Updates delay for a specific date on the server.
         * 
         * @param date The date to update
         * @param delayMinutes The new delay in minutes
         * @return true if update was successful, false otherwise
         * @throws ConnectionInterruptedException if connection is lost
         */
        public boolean updateDelay(LocalDate date, int delayMinutes) throws ConnectionInterruptedException {
            if (!connectionActive) {
                throw new ConnectionInterruptedException("Connection to SMOS server interrupted");
            }
            
            // Simulate network latency
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Simulate occasional connection failure (10% chance)
            if (Math.random() < 0.1) {
                connectionActive = false;
                throw new ConnectionInterruptedException("Connection to SMOS server interrupted during transmission");
            }
            
            serverDelays.put(date, delayMinutes);
            return true;
        }
        
        /**
         * Gets the current delay for a specific date from the server.
         * 
         * @param date The date to query
         * @return The delay in minutes, or null if not found
         * @throws ConnectionInterruptedException if connection is lost
         */
        public Integer getDelay(LocalDate date) throws ConnectionInterruptedException {
            if (!connectionActive) {
                throw new ConnectionInterruptedException("Connection to SMOS server interrupted");
            }
            
            return serverDelays.get(date);
        }
        
        /**
         * Checks if the server connection is active.
         * 
         * @return true if connection is active, false otherwise
         */
        public boolean isConnectionActive() {
            return connectionActive;
        }
        
        /**
         * Attempts to reconnect to the server.
         * 
         * @return true if reconnection was successful, false otherwise
         */
        public boolean reconnect() {
            // Simulate reconnection attempt
            connectionActive = Math.random() > 0.3; // 70% success rate
            return connectionActive;
        }
    }
    
    /**
     * Custom exception for server connection interruptions.
     */
    static class ConnectionInterruptedException extends Exception {
        public ConnectionInterruptedException(String message) {
            super(message);
        }
    }
    
    /**
     * Main application class that manages the EditDelay use case flow.
     */
    private Administrator admin;
    private SmosServer server;
    private Scanner scanner;
    private boolean operationCancelled;
    
    public EditDelay() {
        // Create default administrator (in real application, credentials would be stored securely)
        this.admin = new Administrator("admin", "admin123");
        this.server = new SmosServer();
        this.scanner = new Scanner(System.in);
        this.operationCancelled = false;
    }
    
    /**
     * Main entry point of the program.
     */
    public void run() {
        System.out.println("=== EditDelay System ===");
        System.out.println("Welcome to the Delay Management System");
        
        try {
            // Step 1: Administrator login
            if (!performLogin()) {
                System.out.println("Login failed. Exiting system.");
                return;
            }
            
            // Main application loop
            while (admin.isLoggedIn() && !operationCancelled) {
                displayMainMenu();
                int choice = getMenuChoice(1, 3);
                
                switch (choice) {
                    case 1:
                        editDelayForDate();
                        break;
                    case 2:
                        viewDelays();
                        break;
                    case 3:
                        admin.logout();
                        System.out.println("Logged out successfully.");
                        break;
                }
            }
            
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
        
        System.out.println("Thank you for using the EditDelay System.");
    }
    
    /**
     * Handles administrator login process.
     * 
     * @return true if login successful, false otherwise
     */
    private boolean performLogin() {
        System.out.println("\n=== Administrator Login ===");
        
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;
        
        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            
            System.out.print("Password: ");
            String password = scanner.nextLine();
            
            if (admin.login(username, password)) {
                System.out.println("Login successful! Welcome, " + username + ".");
                return true;
            } else {
                attempts++;
                System.out.println("Invalid credentials. Attempt " + attempts + " of " + MAX_ATTEMPTS);
                
                if (attempts < MAX_ATTEMPTS) {
                    System.out.println("Please try again.");
                }
            }
        }
        
        System.out.println("Maximum login attempts exceeded.");
        return false;
    }
    
    /**
     * Displays the main menu options.
     */
    private void displayMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Edit delay for a date");
        System.out.println("2. View existing delays");
        System.out.println("3. Logout");
        System.out.print("Enter your choice (1-3): ");
    }
    
    /**
     * Gets and validates menu choice from user.
     * 
     * @param min Minimum valid choice
     * @param max Maximum valid choice
     * @return Validated user choice
     */
    private int getMenuChoice(int min, int max) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.print("Invalid choice. Enter a number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a number between " + min + " and " + max + ": ");
            }
        }
    }
    
    /**
     * Core functionality: Edits delay for a specific date.
     * This implements the main use case sequence.
     */
    private void editDelayForDate() {
        System.out.println("\n=== Edit Delay ===");
        
        try {
            // Step 2: Select date (simulating the "SviewTetTingloregistration" pre-step)
            LocalDate selectedDate = selectDate();
            if (selectedDate == null) {
                System.out.println("Date selection cancelled.");
                return;
            }
            
            // Step 1 (from event sequence): Update screen based on selected date
            displayCurrentDelay(selectedDate);
            
            // Step 2: User edits the delay and clicks "Save"
            DelayEntry editedDelay = editDelay(selectedDate);
            if (editedDelay == null) {
                System.out.println("Delay editing cancelled.");
                return;
            }
            
            // Step 3: System sends modified data to server
            saveDelayToServer(editedDelay);
            
        } catch (ConnectionInterruptedException e) {
            System.err.println("Error: " + e.getMessage());
            handleConnectionInterruption();
        }
    }
    
    /**
     * Prompts user to select a date for editing.
     * 
     * @return Selected date, or null if operation cancelled
     */
    private LocalDate selectDate() {
        System.out.println("Select a date to edit the delay.");
        System.out.println("Enter date in format YYYY-MM-DD (or 'cancel' to abort): ");
        
        while (true) {
            System.out.print("Date: ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("cancel")) {
                operationCancelled = true;
                return null;
            }
            
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
                LocalDate date = LocalDate.parse(input, formatter);
                
                // Validate date is not in the past (business rule example)
                if (date.isBefore(LocalDate.now())) {
                    System.out.println("Cannot edit delays for past dates. Please enter a current or future date.");
                    continue;
                }
                
                return date;
                
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD format (e.g., 2024-01-15).");
            }
        }
    }
    
    /**
     * Displays the current delay for the selected date.
     * 
     * @param date The date to display delay for
     * @throws ConnectionInterruptedException if server connection is lost
     */
    private void displayCurrentDelay(LocalDate date) throws ConnectionInterruptedException {
        System.out.println("\n=== Current Delay Information ===");
        System.out.println("Selected date: " + date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        
        Integer currentDelay = server.getDelay(date);
        if (currentDelay != null) {
            System.out.println("Current delay: " + currentDelay + " minutes");
        } else {
            System.out.println("No delay entry found for this date. Creating new entry.");
        }
    }
    
    /**
     * Prompts user to edit the delay for the selected date.
     * 
     * @param date The date for which delay is being edited
     * @return DelayEntry with new delay value, or null if cancelled
     */
    private DelayEntry editDelay(LocalDate date) {
        System.out.println("\n=== Edit Delay Value ===");
        
        while (true) {
            System.out.print("Enter new delay in minutes (0-1440, or 'cancel' to abort): ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("cancel")) {
                operationCancelled = true;
                return null;
            }
            
            try {
                int delay = Integer.parseInt(input);
                
                // Validate delay range (0 minutes to 24 hours)
                if (delay < 0 || delay > 1440) {
                    System.out.println("Delay must be between 0 and 1440 minutes (24 hours).");
                    continue;
                }
                
                return new DelayEntry(date, delay);
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
    
    /**
     * Saves the modified delay to the server.
     * Implementation of Step 3 from the event sequence.
     * 
     * @param delayEntry The delay entry to save
     * @throws ConnectionInterruptedException if server connection is lost
     */
    private void saveDelayToServer(DelayEntry delayEntry) throws ConnectionInterruptedException {
        System.out.println("\n=== Saving Delay ===");
        System.out.println("Sending data to server...");
        
        try {
            boolean success = server.updateDelay(delayEntry.getDate(), delayEntry.getDelayMinutes());
            
            if (success) {
                System.out.println("✓ Delay saved successfully!");
                System.out.println("Updated: " + delayEntry);
                
                // Postcondition: System remains on the registry screen
                System.out.println("Remaining on the registry screen...");
            }
            
        } catch (ConnectionInterruptedException e) {
            // Postcondition: Connection to SMOS server interrupted
            throw e;
        }
    }
    
    /**
     * Displays existing delays from the server.
     */
    private void viewDelays() {
        System.out.println("\n=== View Existing Delays ===");
        
        if (!server.isConnectionActive()) {
            System.out.println("Server connection is not active. Cannot retrieve delays.");
            return;
        }
        
        System.out.println("Note: This is a simulation showing sample delay entries.");
        System.out.println("In a real implementation, this would fetch all delays from the server.");
        
        // Display sample delays
        System.out.println("\nSample Delay Entries:");
        System.out.println("1. " + new DelayEntry(LocalDate.now(), 30));
        System.out.println("2. " + new DelayEntry(LocalDate.now().plusDays(1), 45));
        System.out.println("3. " + new DelayEntry(LocalDate.now().plusDays(7), 60));
    }
    
    /**
     * Handles server connection interruption.
     * Implements the postcondition for connection interruption.
     */
    private void handleConnectionInterruption() {
        System.out.println("\n=== Connection Interruption Handling ===");
        System.out.println("Connection to SMOS server has been interrupted.");
        
        int attempts = 0;
        final int MAX_RECONNECT_ATTEMPTS = 3;
        
        while (attempts < MAX_RECONNECT_ATTEMPTS) {
            System.out.print("Attempt to reconnect? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            
            if (response.equals("yes")) {
                attempts++;
                System.out.println("Attempting to reconnect (" + attempts + "/" + MAX_RECONNECT_ATTEMPTS + ")...");
                
                if (server.reconnect()) {
                    System.out.println("✓ Reconnection successful!");
                    return;
                } else {
                    System.out.println("Reconnection failed.");
                    
                    if (attempts < MAX_RECONNECT_ATTEMPTS) {
                        System.out.println("Would you like to try again?");
                    }
                }
            } else if (response.equals("no")) {
                System.out.println("Reconnection cancelled. Returning to main menu.");
                break;
            } else {
                System.out.println("Please answer 'yes' or 'no'.");
            }
        }
        
        if (!server.isConnectionActive()) {
            System.out.println("Could not reconnect to server. Some features may be unavailable.");
        }
    }
    
    /**
     * Main method to run the application.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        EditDelay app = new EditDelay();
        app.run();
    }
}