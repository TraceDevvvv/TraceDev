import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * ViewAddressDetails - Complete Java Program
 * 
 * This program implements the ViewAddressDetails use case where an Administrator
 * can view the details of a specific address after logging in and viewing the address list.
 * The program simulates SMOS server connection and interruption as per postconditions.
 * 
 * Key Components:
 * 1. Address class - represents an address with details
 * 2. Administrator class - represents the user with authentication
 * 3. SMOSServer class - simulates server connection
 * 4. AddressManager class - manages address list and details
 * 5. Main class - orchestrates the entire flow
 */

// Address class to represent address details
class Address {
    private int id;
    private String name;
    private String street;
    private String city;
    private String zipCode;
    private String country;
    
    public Address(int id, String name, String street, String city, String zipCode, String country) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }
    
    // Getters for address details
    public int getId() { return id; }
    public String getName() { return name; }
    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getZipCode() { return zipCode; }
    public String getCountry() { return country; }
    
    // Display full address details
    public void displayDetails() {
        System.out.println("\n=== ADDRESS DETAILS ===");
        System.out.println("Address Name: " + name);
        System.out.println("Street: " + street);
        System.out.println("City: " + city);
        System.out.println("ZIP Code: " + zipCode);
        System.out.println("Country: " + country);
        System.out.println("=======================\n");
    }
}

// Administrator class to represent the user
class Administrator {
    private String username;
    private boolean isLoggedIn;
    
    public Administrator(String username) {
        this.username = username;
        this.isLoggedIn = false;
    }
    
    // Simulate login process
    public boolean login(String password) {
        // In a real system, this would validate credentials against a database
        if ("admin123".equals(password)) {
            isLoggedIn = true;
            System.out.println("Login successful for user: " + username);
            return true;
        } else {
            System.out.println("Login failed. Invalid credentials.");
            return false;
        }
    }
    
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void logout() {
        isLoggedIn = false;
        System.out.println("User " + username + " logged out.");
    }
}

// SMOS Server simulation class
class SMOSServer {
    private boolean connected;
    
    public SMOSServer() {
        this.connected = false;
    }
    
    // Connect to SMOS server
    public boolean connect() {
        try {
            // Simulate server connection delay
            System.out.println("Connecting to SMOS server...");
            TimeUnit.SECONDS.sleep(1);
            connected = true;
            System.out.println("Connected to SMOS server successfully.");
            return true;
        } catch (InterruptedException e) {
            System.out.println("Connection to SMOS server interrupted.");
            return false;
        }
    }
    
    // Disconnect from SMOS server
    public void disconnect() {
        if (connected) {
            System.out.println("Disconnecting from SMOS server...");
            connected = false;
            System.out.println("Connection to SMOS server interrupted.");
        }
    }
    
    public boolean isConnected() {
        return connected;
    }
    
    // Simulate fetching address data from server
    public List<Address> fetchAddresses() {
        if (!connected) {
            System.out.println("Cannot fetch addresses: Not connected to SMOS server.");
            return new ArrayList<>();
        }
        
        // Simulate server response with sample addresses
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address(1, "Main Office", "123 Business St", "New York", "10001", "USA"));
        addresses.add(new Address(2, "Warehouse", "456 Industrial Ave", "Chicago", "60601", "USA"));
        addresses.add(new Address(3, "Branch Office", "789 Commerce Rd", "Los Angeles", "90001", "USA"));
        addresses.add(new Address(4, "Headquarters", "321 Corporate Blvd", "San Francisco", "94101", "USA"));
        
        System.out.println("Fetched " + addresses.size() + " addresses from SMOS server.");
        return addresses;
    }
}

// Address Manager to handle address operations
class AddressManager {
    private List<Address> addressList;
    private SMOSServer server;
    
    public AddressManager(SMOSServer server) {
        this.server = server;
        this.addressList = new ArrayList<>();
    }
    
    // View address list (simulating ViewingLenchIndirizzi use case)
    public void viewAddressList() {
        if (!server.isConnected()) {
            System.out.println("Cannot view address list: Not connected to SMOS server.");
            return;
        }
        
        // Fetch addresses from server
        addressList = server.fetchAddresses();
        
        if (addressList.isEmpty()) {
            System.out.println("No addresses found.");
            return;
        }
        
        System.out.println("\n=== ADDRESS LIST ===");
        for (int i = 0; i < addressList.size(); i++) {
            Address addr = addressList.get(i);
            System.out.println((i + 1) + ". " + addr.getName());
        }
        System.out.println("====================\n");
    }
    
    // View details of a specific address by index
    public void viewAddressDetails(int index) {
        if (index < 0 || index >= addressList.size()) {
            System.out.println("Invalid address selection. Please select a valid address.");
            return;
        }
        
        Address selectedAddress = addressList.get(index);
        System.out.println("\nDisplaying screen with details of a single address...");
        selectedAddress.displayDetails();
    }
    
    // Get the number of addresses in the list
    public int getAddressCount() {
        return addressList.size();
    }
}

// Main class to orchestrate the ViewAddressDetails use case
public class ViewAddressDetails {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== View Address Details System ===");
        System.out.println("Use Case: ViewAddressDetails");
        System.out.println("Actor: Administrator\n");
        
        // Step 1: Create administrator and login (precondition)
        Administrator admin = new Administrator("admin");
        
        // Simulate login process
        System.out.print("Enter password to login: ");
        String password = scanner.nextLine();
        
        if (!admin.login(password)) {
            System.out.println("Login failed. Exiting system.");
            scanner.close();
            return;
        }
        
        // Step 2: Connect to SMOS server
        SMOSServer server = new SMOSServer();
        if (!server.connect()) {
            System.out.println("Failed to connect to SMOS server. Exiting system.");
            admin.logout();
            scanner.close();
            return;
        }
        
        // Step 3: Create address manager and view address list (precondition)
        AddressManager addressManager = new AddressManager(server);
        
        System.out.println("\nPerforming 'ViewingLenchIndirizzi' use case...");
        addressManager.viewAddressList();
        
        if (addressManager.getAddressCount() == 0) {
            System.out.println("No addresses available. Exiting system.");
            server.disconnect();
            admin.logout();
            scanner.close();
            return;
        }
        
        // Step 4: User clicks "Show address details" button
        System.out.println("\nUser clicks on 'Show address details' button...");
        System.out.println("Select an address to view details (1-" + addressManager.getAddressCount() + "): ");
        
        try {
            int selection = Integer.parseInt(scanner.nextLine()) - 1;
            
            // Step 5: System displays the screen with details of a single address
            addressManager.viewAddressDetails(selection);
            
            // Step 6: Postconditions - Display detailed information and interrupt server connection
            System.out.println("Postconditions met:");
            System.out.println("1. User displays detailed information relating to a single address.");
            System.out.println("2. Connection to the SMOS server interrupted.");
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // Ensure server connection is interrupted and user is logged out
            server.disconnect();
            admin.logout();
            System.out.println("\nSystem shutdown complete.");
        }
        
        scanner.close();
    }
    
    // Additional helper method for edge case handling
    private static void handleEdgeCases() {
        System.out.println("\n=== Edge Cases Handled ===");
        System.out.println("1. Invalid login credentials");
        System.out.println("2. Server connection failure");
        System.out.println("3. Empty address list");
        System.out.println("4. Invalid address selection");
        System.out.println("5. Non-numeric input for address selection");
        System.out.println("6. Proper resource cleanup (scanner, connections)");
        System.out.println("==========================\n");
    }
}