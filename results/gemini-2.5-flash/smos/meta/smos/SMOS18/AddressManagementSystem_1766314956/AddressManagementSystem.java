package com.address_management_system;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main system class orchestrating the address management interactions.
 * This class simulates the "EnterNewAddress" use case, including user login,
 * displaying addresses, prompting for new address input, validating data,
 * and handling various error conditions like invalid input or connection issues.
 */
public class AddressManagementSystem {
    private static final Logger LOGGER = Logger.getLogger(AddressManagementSystem.class.getName());

    private final AddressArchive addressArchive;
    private Administrator currentAdmin; // Represents the logged-in administrator

    /**
     * Constructs a new AddressManagementSystem.
     * Initializes the AddressArchive and sets up a default administrator.
     */
    public AddressManagementSystem() {
        this.addressArchive = new AddressArchive();
        // For simulation purposes, create a default administrator
        this.currentAdmin = new Administrator("admin", "password123");
        LOGGER.log(Level.INFO, "AddressManagementSystem initialized.");
    }

    /**
     * Simulates the administrator login process.
     * This is a precondition for entering a new address.
     *
     * @param username The username to attempt login with.
     * @param password The password to attempt login with.
     * @return true if login is successful, false otherwise.
     */
    public boolean simulateAdminLogin(String username, String password) {
        LOGGER.log(Level.INFO, "Attempting to log in administrator '{0}'.", username);
        if (currentAdmin.login(username, password)) {
            LOGGER.log(Level.INFO, "Administrator '{0}' successfully logged in.", username);
            return true;
        } else {
            LOGGER.log(Level.WARNING, "Administrator login failed for '{0}'.", username);
            return false;
        }
    }

    /**
     * Simulates the "ViewingLenchindirizzi" use case, where the system displays
     * the current list of addresses. This is a precondition for entering a new address.
     */
    public void simulateViewingAddresses() {
        LOGGER.log(Level.INFO, "Simulating 'ViewingLenchindirizzi': Displaying current addresses.");
        Set<Address> addresses = addressArchive.getAllAddresses();
        if (addresses.isEmpty()) {
            System.out.println("No addresses currently in the archive.");
        } else {
            System.out.println("--- Current Addresses ---");
            addresses.forEach(address -> System.out.println("- " + address.getName()));
            System.out.println("-------------------------");
        }
    }

    /**
     * Simulates a potential connection error to an external SMOS server.
     * This method introduces a random chance of failure to cover the postcondition
     * "the connection data error to the SMOS server interrupted".
     *
     * @throws SMOSConnectionException if the simulated connection fails.
     */
    private void simulateSMOSConnection() {
        // Simulate a 10% chance of SMOS connection failure
        if (ThreadLocalRandom.current().nextInt(10) == 0) {
            LOGGER.log(Level.SEVERE, "Simulated SMOS server connection failure.");
            throw new SMOSConnectionException("Failed to connect to SMOS server during data synchronization.");
        }
    }

    /**
     * Implements the core "EnterNewAddress" use case logic.
     * This method guides the administrator through filling out the form,
     * validating input, and saving the new address.
     *
     * @param scanner The Scanner object for user input.
     * @throws IllegalStateException if the administrator is not logged in.
     */
    public void enterNewAddress(Scanner scanner) {
        // Precondition check: User is logged in as an administrator
        if (!currentAdmin.isLoggedIn()) {
            LOGGER.log(Level.SEVERE, "Attempted to enter new address without administrator being logged in.");
            throw new IllegalStateException("Administrator must be logged in to enter a new address.");
        }

        LOGGER.log(Level.INFO, "Administrator '{0}' clicked 'New address' button.", currentAdmin.getUsername());
        System.out.println("\n--- Enter New Address ---");
        System.out.println("Please fill out the form below. Type 'cancel' at any point to interrupt the operation.");

        String addressNameInput = null;
        boolean inputReceived = false;

        // System 1. The system shows the form to be completed with: address name.
        while (!inputReceived) {
            System.out.print("Address Name: ");
            addressNameInput = scanner.nextLine();

            if (addressNameInput.equalsIgnoreCase("cancel")) {
                LOGGER.log(Level.INFO, "Administrator '{0}' interrupted the operation.", currentAdmin.getUsername());
                System.out.println("Operation interrupted by administrator.");
                // Postcondition: the administrator interrupts the operation
                return;
            }

            // User 2. Fill out the form (validation happens implicitly in Address constructor and addAddress)
            // User 3. Click on the "Save" button (simulated by proceeding after input)
            try {
                // System 4. Checks on the validity of the data entered
                // Address constructor handles null/empty validation
                Address newAddress = new Address(addressNameInput);

                // Simulate SMOS connection before saving to cover potential errors
                simulateSMOSConnection();

                // System 4. ...and inserts a new address in the archive
                if (addressArchive.addAddress(newAddress)) {
                    // Postcondition: The user has entered an address is notified
                    System.out.println("SUCCESS: New address '" + newAddress.getName() + "' has been successfully added to the archive.");
                    LOGGER.log(Level.INFO, "New address '{0}' added by administrator '{1}'.",
                            new Object[]{newAddress.getName(), currentAdmin.getUsername()});
                    inputReceived = true; // Exit loop on success
                } else {
                    // Address already exists, activate "Errodati" use case
                    throw new ValidationException("An address with the name '" + newAddress.getName() + "' already exists. Please enter a unique name.");
                }
            } catch (IllegalArgumentException | ValidationException e) {
                // In the event that the data entered are not valid, activates the case of "Errodati" use.
                LOGGER.log(Level.WARNING, "Data validation error (Errodati): {0}", e.getMessage());
                System.err.println("ERROR (Errodati): " + e.getMessage());
                System.out.println("Please try again or type 'cancel' to interrupt.");
                // Loop continues to prompt for input again
            } catch (SMOSConnectionException e) {
                // Postcondition: the connection data error to the SMOS server interrupted
                LOGGER.log(Level.SEVERE, "SMOS Connection Error: {0}", e.getMessage());
                System.err.println("CRITICAL ERROR: " + e.getMessage());
                System.err.println("The operation was interrupted due to a connection error with the SMOS server.");
                // Postcondition: the administrator interrupts the operation (implicitly, as the operation cannot proceed)
                return; // Exit the operation due to critical error
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "An unexpected error occurred: {0}", e.getMessage(), e);
                System.err.println("An unexpected error occurred: " + e.getMessage());
                System.err.println("Operation interrupted due to an unhandled system error.");
                return; // Exit the operation due to unexpected error
            }
        }
    }

    /**
     * Main method to run the Address Management System simulation.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Configure logging to show INFO messages and above
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tF %1$tT] [%4$-7s] %3$s - %5$s%n");
        LOGGER.setLevel(Level.INFO);

        AddressManagementSystem system = new AddressManagementSystem();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Address Management System!");

        // Simulate Precondition: Administrator logs in
        System.out.println("\n--- Administrator Login ---");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (!system.simulateAdminLogin(username, password)) {
            System.err.println("Login failed. Exiting system.");
            scanner.close();
            return;
        }

        // Simulate Precondition: Administrator views existing addresses
        system.simulateViewingAddresses();

        // Simulate the "New address" button click and subsequent flow
        // Loop to allow multiple address entries or cancellation
        while (true) {
            System.out.println("\nReady to enter a new address. (Type 'exit' to quit the system)");
            System.out.print("Press Enter to proceed with 'New address' or type 'exit': ");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("exit")) {
                System.out.println("Exiting Address Management System. Goodbye!");
                break;
            }

            try {
                system.enterNewAddress(scanner);
            } catch (IllegalStateException e) {
                System.err.println("System Error: " + e.getMessage());
                System.err.println("Please ensure all preconditions are met before attempting the operation.");
                break; // Exit if a critical system state error occurs
            }
            // After an operation, show current addresses again for context
            system.simulateViewingAddresses();
        }

        scanner.close();
        system.currentAdmin.logout(); // Ensure admin is logged out on exit
    }
}