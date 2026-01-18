/*
 * ViewAddressList.java
 * A complete Java program for the ViewAddressList use case.
 * Simulates displaying address list for an administrator.
 * Preconditions: User is logged in as administrator and clicked "Address Management".
 * Postconditions: System shows address list and connection to SMOS server is interrupted.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an address entry in the system.
 */
class Address {
    private int id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;

    /**
     * Constructor to create an Address object.
     */
    public Address(int id, String street, String city, String state, String zipCode, String country) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }

    // Getters for address attributes
    public int getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }

    /**
     * Returns a formatted string representation of the address.
     */
    @Override
    public String toString() {
        return String.format("ID: %d | %s, %s, %s %s, %s", id, street, city, state, zipCode, country);
    }
}

/**
 * Service class that handles address-related operations.
 * Simulates interaction with an address archive and SMOS server.
 */
class AddressService {
    private boolean isConnectedToSMOS = false;

    /**
     * Simulates connecting to the SMOS server.
     * In a real system, this would establish a network connection.
     */
    public void connectToSMOS() {
        System.out.println("Connecting to SMOS server...");
        // Simulate connection delay
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isConnectedToSMOS = true;
        System.out.println("Connected to SMOS server successfully.");
    }

    /**
     * Simulates fetching addresses from the archive.
     * In a real system, this would query a database or external service.
     */
    public List<Address> fetchAddressesFromArchive() {
        System.out.println("Fetching addresses from archive...");
        // Simulate network/database delay
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Create and return a sample list of addresses for demonstration
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address(1, "123 Main St", "Springfield", "IL", "62701", "USA"));
        addresses.add(new Address(2, "456 Oak Ave", "Metropolis", "NY", "10001", "USA"));
        addresses.add(new Address(3, "789 Pine Rd", "Gotham", "NJ", "07001", "USA"));
        addresses.add(new Address(4, "101 Maple Dr", "Star City", "CA", "90001", "USA"));
        addresses.add(new Address(5, "202 Elm Blvd", "Central City", "MO", "64101", "USA"));

        return addresses;
    }

    /**
     * Simulates interrupting the connection to the SMOS server.
     * This fulfills the postcondition "Connection to the SMOS server interrupted".
     */
    public void interruptSMOSConnection() {
        if (isConnectedToSMOS) {
            System.out.println("Interrupting connection to SMOS server...");
            // Simulate disconnection delay
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isConnectedToSMOS = false;
            System.out.println("Connection to SMOS server interrupted.");
        } else {
            System.out.println("No active connection to SMOS server.");
        }
    }

    /**
     * Checks if the user is logged in as an administrator.
     * This simulates the authentication check precondition.
     */
    public boolean isUserAdmin(String username, String role) {
        return "Administrator".equalsIgnoreCase(role);
    }
}

/**
 * Main class that orchestrates the ViewAddressList use case.
 * This is the entry point of the program.
 */
public class ViewAddressList {
    public static void main(String[] args) {
        System.out.println("=== View Address List Use Case ===");
        System.out.println("Preconditions:");
        System.out.println("1. User logged in with administrator role.");
        System.out.println("2. User clicked 'Address Management' button.");

        // Simulate user credentials (in a real system, these would come from login session)
        String currentUser = "admin";
        String currentUserRole = "Administrator";

        // Create service instance
        AddressService addressService = new AddressService();

        // Check preconditions: user must be administrator
        if (!addressService.isUserAdmin(currentUser, currentUserRole)) {
            System.out.println("ERROR: User does not have administrator privileges. Access denied.");
            return;
        }

        System.out.println("Preconditions satisfied. Proceeding with address list display...");

        // Step 1: Connect to SMOS server (simulated)
        addressService.connectToSMOS();

        // Step 2: Fetch addresses from archive
        List<Address> addresses = addressService.fetchAddressesFromArchive();

        // Step 3: Display the address list
        System.out.println("\n=== Address List ===");
        System.out.println("Total addresses: " + addresses.size());
        System.out.println("----------------------------------------");
        for (Address addr : addresses) {
            System.out.println(addr.toString());
        }
        System.out.println("----------------------------------------");

        // Step 4: Fulfill postcondition - interrupt SMOS server connection
        System.out.println("\nPostconditions:");
        addressService.interruptSMOSConnection();
        System.out.println("1. System shows the list of addresses in the archive.");
        System.out.println("2. Connection to the SMOS server interrupted.");

        System.out.println("\nUse case completed successfully.");
    }
}