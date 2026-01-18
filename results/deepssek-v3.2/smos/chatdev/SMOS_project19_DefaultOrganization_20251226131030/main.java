/**
 * Main class for the Address Details Viewer application.
 * Implements the ViewAddressDetails use case with simulated user interaction.
 * Preconditions: User must be logged in and must have viewed the address list (ViewingLenchIndirizzi)
 * before selecting an address for detailed view.
 */
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    // Simulated SMOS server connection status
    private static boolean smosConnected = true;
    // Simulated user authentication status
    private static boolean userLoggedIn = false;
    // Simulated address list (in a real application, this would come from a database)
    private static ArrayList<Address> addressList = new ArrayList<>();
    /**
     * Main entry point for the application.
     * Simulates the complete ViewAddressDetails use case flow.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Address Details Viewer ===");
        // Precondition 1: Simulate user login
        simulateLogin(scanner);
        if (!userLoggedIn) {
            System.out.println("Login failed. Exiting application.");
            scanner.close();
            return;
        }
        // Precondition 2: Simulate viewing address list (ViewingLenchIndirizzi use case)
        System.out.println("\n--- Simulating 'ViewingLenchIndirizzi' use case ---");
        initializeAddressList();
        displayAddressList();
        // Check if address list is empty before proceeding
        if (addressList.isEmpty()) {
            System.out.println("Address list is empty. Cannot view details. Exiting.");
            scanner.close();
            return;
        }
        // Simulate User click on "Show address details" button
        System.out.println("\n--- Address Selection ---");
        Address selectedAddress = selectAddressForDetails(scanner);
        if (selectedAddress == null) {
            System.out.println("No valid address selected. Exiting.");
            scanner.close();
            return;
        }
        // System displays screen with details of a single address
        displayAddressDetails(selectedAddress);
        // Postcondition: Simulate SMOS server connection interruption
        disconnectFromSMOS();
        scanner.close();
        System.out.println("\nApplication terminated.");
    }
    /**
     * Simulates user login process.
     * In a real application, this would involve actual authentication.
     * 
     * @param scanner the Scanner object for user input
     */
    private static void simulateLogin(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        // Simple authentication simulation
        if (!username.isEmpty() && !password.isEmpty()) {
            userLoggedIn = true;
            System.out.println("Login successful.");
        } else {
            System.out.println("Login failed - credentials cannot be empty.");
        }
    }
    /**
     * Initializes the address list with sample data.
     */
    private static void initializeAddressList() {
        addressList.add(new Address("Main Street", "New York", "USA", "10001"));
        addressList.add(new Address("Broadway", "Los Angeles", "USA", "90001"));
        addressList.add(new Address("Oxford Street", "London", "UK", "W1D 1BS"));
        addressList.add(new Address("Champs-Élysées", "Paris", "France", "75008"));
    }
    /**
     * Displays the list of addresses to the user.
     * Simulates the address list viewing screen as part of ViewingLenchIndirizzi.
     */
    private static void displayAddressList() {
        System.out.println("\n=== Address List ===");
        for (int i = 0; i < addressList.size(); i++) {
            System.out.println((i + 1) + ". " + addressList.get(i).getName());
        }
        System.out.println("===================");
    }
    /**
     * Prompts user to select an address for viewing details.
     * Handles input validation and edge cases.
     * 
     * @param scanner the Scanner object for user input
     * @return the selected Address object, or null if selection fails
     */
    private static Address selectAddressForDetails(Scanner scanner) {
        System.out.print("Enter address number to view details (1-" + addressList.size() + "): ");
        String input = scanner.nextLine();
        try {
            int choice = Integer.parseInt(input);
            if (choice >= 1 && choice <= addressList.size()) {
                System.out.println("User clicked 'Show address details' button for address #" + choice);
                return addressList.get(choice - 1);
            } else {
                System.out.println("Error: Selection must be between 1 and " + addressList.size() + ".");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input. Please enter a number.");
        }
        return null;
    }
    /**
     * Displays detailed information for a single address.
     * This implements the main functionality of the ViewAddressDetails use case.
     * 
     * @param address the Address object to display details for
     */
    private static void displayAddressDetails(Address address) {
        System.out.println("\n=== Address Details ===");
        System.out.println("Address Name: " + address.getName());
        System.out.println("City: " + address.getCity());
        System.out.println("Country: " + address.getCountry());
        System.out.println("Zip Code: " + address.getZipCode());
        System.out.println("=======================");
        // Postcondition: User has viewed detailed information
        System.out.println("Postcondition: User has displayed detailed information relating to a single address.");
    }
    /**
     * Simulates disconnection from the SMOS server.
     * This handles the postcondition about server connection interruption.
     */
    private static void disconnectFromSMOS() {
        if (smosConnected) {
            smosConnected = false;
            System.out.println("\nPostcondition: Connection to the SMOS server interrupted.");
        }
    }
}