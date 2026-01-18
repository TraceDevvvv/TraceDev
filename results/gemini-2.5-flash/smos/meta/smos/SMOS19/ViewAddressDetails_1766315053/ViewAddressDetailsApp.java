import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents an Address entity with basic details.
 * In a real-world scenario, this might include more fields like street, city, postal code, etc.
 */
class Address {
    private String name;
    private String street;
    private String city;
    private String zipCode;
    private String country;

    /**
     * Constructs a new Address object.
     *
     * @param name    A descriptive name for the address (e.g., "Main Office", "Warehouse A").
     * @param street  The street name and number.
     * @param city    The city.
     * @param zipCode The postal or zip code.
     * @param country The country.
     */
    public Address(String name, String street, String city, String zipCode, String country) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    /**
     * Returns the descriptive name of the address.
     *
     * @return The address name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a full string representation of the address.
     *
     * @return A formatted string containing all address details.
     */
    @Override
    public String toString() {
        return String.format("%s: %s, %s, %s, %s", name, street, city, zipCode, country);
    }
}

/**
 * Simulates a connection to an SMOS (System Management and Operations System) server.
 * This class manages the connection state.
 */
class SMOSServerConnection {
    private boolean isConnected;

    /**
     * Constructs a new SMOSServerConnection, initially disconnected.
     */
    public SMOSServerConnection() {
        this.isConnected = false;
    }

    /**
     * Establishes a connection to the SMOS server.
     */
    public void connect() {
        if (!isConnected) {
            System.out.println("[SMOS Server] Attempting to connect to SMOS server...");
            // Simulate connection delay or logic
            this.isConnected = true;
            System.out.println("[SMOS Server] Connection to SMOS server established successfully.");
        } else {
            System.out.println("[SMOS Server] Already connected to SMOS server.");
        }
    }

    /**
     * Disconnects from the SMOS server.
     */
    public void disconnect() {
        if (isConnected) {
            System.out.println("[SMOS Server] Disconnecting from SMOS server...");
            // Simulate disconnection delay or logic
            this.isConnected = false;
            System.out.println("[SMOS Server] Connection to SMOS server interrupted.");
        } else {
            System.out.println("[SMOS Server] SMOS server is already disconnected.");
        }
    }

    /**
     * Checks if the SMOS server is currently connected.
     *
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        return isConnected;
    }
}

/**
 * Main application class for the "View Address Details" use case.
 * This class orchestrates the simulation of user interactions and system responses.
 */
public class ViewAddressDetailsApp {

    private static boolean isLoggedIn = false;
    private static List<Address> addressList = new ArrayList<>();
    private static SMOSServerConnection smosConnection = new SMOSServerConnection();
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Initializes the application with sample data.
     */
    private static void initializeData() {
        addressList.add(new Address("Headquarters", "123 Main St", "Anytown", "10001", "USA"));
        addressList.add(new Address("Warehouse A", "456 Industrial Rd", "Otherville", "20002", "Canada"));
        addressList.add(new Address("Branch Office", "789 Business Blvd", "Metropolis", "30003", "UK"));
    }

    /**
     * Simulates the user login process.
     * As per preconditions, we assume the user is already logged in.
     */
    private static void simulateLogin() {
        System.out.println("\n--- Precondition: User Login ---");
        System.out.println("Administrator has successfully logged in.");
        isLoggedIn = true;
    }

    /**
     * Simulates the "ViewingLenchIndirizzi" use case, displaying a list of addresses
     * and prompting the user to select one.
     *
     * @return The selected Address object, or null if an invalid selection is made.
     */
    private static Address simulateViewingAddressList() {
        if (!isLoggedIn) {
            System.out.println("Error: User is not logged in. Cannot view address list.");
            return null;
        }

        System.out.println("\n--- Precondition: Viewing Address List (ViewingLenchIndirizzi) ---");
        System.out.println("System is displaying the address list:");

        if (addressList.isEmpty()) {
            System.out.println("No addresses available to display.");
            return null;
        }

        for (int i = 0; i < addressList.size(); i++) {
            System.out.println((i + 1) + ". " + addressList.get(i).getName());
        }

        System.out.print("Enter the number of the address to view details (or 0 to cancel): ");
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return null;
        }

        if (choice == 0) {
            System.out.println("Address detail viewing cancelled.");
            return null;
        }

        if (choice > 0 && choice <= addressList.size()) {
            System.out.println("User clicked on 'Show address details' button for address #" + choice + ".");
            return addressList.get(choice - 1);
        } else {
            System.out.println("Invalid address selection. Please try again.");
            return null;
        }
    }

    /**
     * Displays the details of a single address, specifically its name, as per the use case.
     *
     * @param address The Address object whose details are to be displayed.
     */
    private static void displayAddressDetails(Address address) {
        if (address == null) {
            System.out.println("No address selected or invalid address provided for display.");
            return;
        }

        System.out.println("\n--- Event Sequence: System Displays Address Details ---");
        System.out.println("Displaying screen with details of a single address:");
        System.out.println("Address Name: " + address.getName());
        // In a real application, more details would be displayed here, e.g.:
        // System.out.println("Street: " + address.getStreet());
        // System.out.println("City: " + address.getCity());
        // System.out.println("Zip Code: " + address.getZipCode());
        // System.out.println("Country: " + address.getCountry());
    }

    /**
     * Simulates the postcondition of the SMOS server connection being interrupted.
     * This involves ensuring a connection exists and then explicitly disconnecting it.
     */
    private static void simulateSMOSConnectionInterruption() {
        System.out.println("\n--- Postcondition: SMOS Server Connection Interruption ---");
        // Ensure there's an active connection to interrupt
        if (!smosConnection.isConnected()) {
            smosConnection.connect(); // Connect if not already connected, to then interrupt it.
        }
        smosConnection.disconnect(); // Interrupt the connection
    }

    /**
     * The main entry point of the application.
     * Executes the sequence of events for the "View Address Details" use case.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        initializeData(); // Setup sample addresses

        System.out.println("--- Starting View Address Details Use Case Simulation ---");

        // 1. Precondition: User has already logged in
        simulateLogin();

        // 2. Precondition: User has already performed "ViewingLenchIndirizzi"
        //    and the system is viewing the address list.
        Address selectedAddress = simulateViewingAddressList();

        // Proceed only if an address was successfully selected
        if (selectedAddress != null) {
            // 3. Precondition: User clicks on the "Show address details" button
            //    (This is implicitly handled by the selection in simulateViewingAddressList)

            // Event Sequence: System displays the screen with details of a single address
            displayAddressDetails(selectedAddress);

            // Postcondition: The user displays detailed information relating to a single address.
            // (This is fulfilled by the displayAddressDetails method)

            // Postcondition: Connection to the SMOS server interrupted
            simulateSMOSConnectionInterruption();
        } else {
            System.out.println("\nUse case 'View Address Details' terminated due to invalid selection or cancellation.");
        }

        System.out.println("\n--- View Address Details Use Case Simulation Finished ---");
        scanner.close(); // Close the scanner to release system resources
    }
}