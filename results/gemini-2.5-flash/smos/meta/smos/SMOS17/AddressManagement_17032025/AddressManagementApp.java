package com.addressmanagement;

import java.util.List;
import java.util.Scanner;

/**
 * Main application class to simulate the "View Address List" use case.
 * It demonstrates administrator login, accessing address management,
 * displaying the address list, and handling SMOS server connection.
 */
public class AddressManagementApp {

    public static void main(String[] args) {
        // Initialize components
        // In a real application, these would likely be injected or managed by a framework.
        Administrator admin = new Administrator("admin", "adminpass");
        AddressService addressService = new AddressService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Address Management System ---");

        // Precondition: The user is logged in to the system with the administrator role
        System.out.println("\nAttempting Administrator Login...");
        boolean loggedIn = false;
        int loginAttempts = 0;
        final int MAX_LOGIN_ATTEMPTS = 3;

        while (!loggedIn && loginAttempts < MAX_LOGIN_ATTEMPTS) {
            System.out.print("Enter username: ");
            String enteredUsername = scanner.nextLine();
            System.out.print("Enter password: ");
            String enteredPassword = scanner.nextLine();

            if (admin.login(enteredUsername, enteredPassword)) {
                loggedIn = true;
            } else {
                loginAttempts++;
                System.out.println("Login failed. " + (MAX_LOGIN_ATTEMPTS - loginAttempts) + " attempts remaining.");
            }
        }

        if (!loggedIn) {
            System.out.println("Maximum login attempts reached. Exiting application.");
            scanner.close();
            return; // Exit if login fails
        }

        // Simulate connection to SMOS server (if required for fetching data)
        // Although the use case states "Connection to the SMOS server interrupted" as a postcondition,
        // it implies there was a connection to begin with.
        addressService.connectToSMOS();

        // Precondition: The user clicks on the "Address Management" button
        System.out.println("\nAdministrator clicks on 'Address Management' button...");
        System.out.println("Press Enter to view the address list...");
        scanner.nextLine(); // Wait for user input to simulate button click

        // Event Sequence: System displays the address list.
        System.out.println("\n--- Displaying Address List ---");
        List<Address> addresses = addressService.getAddresses();

        if (addresses.isEmpty()) {
            System.out.println("No addresses found in the archive.");
        } else {
            for (int i = 0; i < addresses.size(); i++) {
                System.out.println((i + 1) + ". " + addresses.get(i));
            }
        }

        // Postcondition: The system shows the list of addresses in the archive (already done above)
        // Postcondition: Connection to the SMOS server interrupted
        System.out.println("\n--- Post-operation actions ---");
        addressService.disconnectFromSMOS();

        // Simulate administrator logging out
        admin.logout();

        System.out.println("\n--- Application Finished ---");
        scanner.close();
    }
}