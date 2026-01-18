// SearchTouristUseCase.java
// This class implements the SearchTourist use case as a console application.
// It simulates the agency operator accessing search functionality, filling out the form,
// submitting the request, and processing the search with handling of server connection interruptions.

import java.util.List;
import java.util.Scanner;

/**
 * Console-based implementation of the SearchTourist use case.
 * Simulates the complete flow from agency operator login to search results display.
 * Handles server connection interruptions to the ETOUR server.
 */
public class SearchTouristUseCase {
    
    private TouristService touristService;
    private Scanner scanner;
    private boolean agencyLoggedIn;
    
    /**
     * Constructor initializes the use case components.
     */
    public SearchTouristUseCase() {
        this.touristService = new TouristService();
        this.scanner = new Scanner(System.in);
        this.agencyLoggedIn = false;
    }
    
    /**
     * Simulates agency operator login.
     * In a real system, this would involve authentication against a user database.
     * For simulation purposes, we simply prompt for credentials and validate them.
     */
    private void simulateAgencyLogin() {
        System.out.println("\n=== ETOUR System - Agency Operator Login ===");
        System.out.println("Simulating agency login...");
        
        // Simulated credentials (in a real system, these would be validated against a database)
        final String SIMULATED_USERNAME = "agency_operator";
        final String SIMULATED_PASSWORD = "agency123";
        
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        // Simple credential validation
        if (SIMULATED_USERNAME.equals(username) && SIMULATED_PASSWORD.equals(password)) {
            agencyLoggedIn = true;
            System.out.println("Login successful! Welcome, Agency Operator.");
        } else {
            System.out.println("Invalid credentials. Login failed.");
            agencyLoggedIn = false;
        }
    }
    
    /**
     * Displays the search form and collects search parameters from the user.
     * @return SearchParameters object populated with user input
     */
    private SearchParameters displaySearchForm() {
        System.out.println("\n=== Tourist Search Form ===");
        System.out.println("Enter search criteria (press Enter to skip any field):");
        
        SearchParameters parameters = new SearchParameters();
        
        System.out.print("Account ID: ");
        String accountId = scanner.nextLine().trim();
        if (!accountId.isEmpty()) {
            parameters.setAccountId(accountId);
        }
        
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        if (!username.isEmpty()) {
            parameters.setUsername(username);
        }
        
        System.out.print("First Name: ");
        String firstName = scanner.nextLine().trim();
        if (!firstName.isEmpty()) {
            parameters.setFirstName(firstName);
        }
        
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine().trim();
        if (!lastName.isEmpty()) {
            parameters.setLastName(lastName);
        }
        
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        if (!email.isEmpty()) {
            parameters.setEmail(email);
        }
        
        System.out.print("Nationality: ");
        String nationality = scanner.nextLine().trim();
        if (!nationality.isEmpty()) {
            parameters.setNationality(nationality);
        }
        
        System.out.print("Phone Number: ");
        String phoneNumber = scanner.nextLine().trim();
        if (!phoneNumber.isEmpty()) {
            parameters.setPhoneNumber(phoneNumber);
        }
        
        System.out.print("Active Status (true/false) or leave empty: ");
        String activeStatusStr = scanner.nextLine().trim();
        if (!activeStatusStr.isEmpty()) {
            if (activeStatusStr.equalsIgnoreCase("true") || activeStatusStr.equalsIgnoreCase("false")) {
                parameters.setActiveStatus(Boolean.parseBoolean(activeStatusStr));
            } else {
                System.out.println("Invalid active status. Using default (no filter).");
            }
        }
        
        return parameters;
    }
    
    /**
     * Processes the search request using TouristService.
     * Handles server connection interruptions and retries.
     * @param parameters The search criteria
     * @return List of matching tourist accounts
     */
    private List<TouristAccount> processSearchRequest(SearchParameters parameters) {
        System.out.println("\n=== Processing Search Request ===");
        
        try {
            // Perform search through TouristService
            List<TouristAccount> results = touristService.searchTouristAccounts(parameters);
            
            System.out.println("Search completed successfully.");
            return results;
            
        } catch (TouristService.ServerConnectionException e) {
            System.out.println("ERROR: " + e.getMessage());
            System.out.println("Search cannot be processed due to server connection issues.");
            return null;
        } catch (Exception e) {
            System.out.println("ERROR: An unexpected error occurred: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Displays search results in a formatted manner.
     * @param results List of tourist accounts to display
     */
    private void displaySearchResults(List<TouristAccount> results) {
        if (results == null) {
            System.out.println("No results to display (search failed).");
            return;
        }
        
        System.out.println("\n=== Search Results ===");
        System.out.println("Found " + results.size() + " tourist account(s) matching your criteria:");
        
        if (results.isEmpty()) {
            System.out.println("No tourist accounts found matching the specified criteria.");
            System.out.println("Try broadening your search parameters.");
        } else {
            System.out.println("\n==================================================================");
            System.out.printf("%-10s %-15s %-20s %-25s %-15s %-10s%n", 
                    "Account ID", "Username", "Full Name", "Email", "Nationality", "Active");
            System.out.println("==================================================================");
            
            for (TouristAccount account : results) {
                System.out.printf("%-10s %-15s %-20s %-25s %-15s %-10s%n",
                        account.getAccountId(),
                        account.getUsername(),
                        account.getFullName(),
                        account.getEmail(),
                        account.getNationality(),
                        account.isActive() ? "Yes" : "No");
            }
            System.out.println("==================================================================");
            
            // Option to view detailed information for a specific account
            System.out.print("\nEnter Account ID to view detailed information (or press Enter to skip): ");
            String selectedAccountId = scanner.nextLine().trim();
            
            if (!selectedAccountId.isEmpty()) {
                displayAccountDetails(selectedAccountId, results);
            }
        }
    }
    
    /**
     * Displays detailed information for a specific tourist account.
     * @param accountId The account ID to display details for
     * @param results List of search results to find the account in
     */
    private void displayAccountDetails(String accountId, List<TouristAccount> results) {
        for (TouristAccount account : results) {
            if (account.matchesAccountId(accountId)) {
                System.out.println("\n=== Detailed Account Information ===");
                System.out.println("Account ID: " + account.getAccountId());
                System.out.println("Username: " + account.getUsername());
                System.out.println("Full Name: " + account.getFullName());
                System.out.println("First Name: " + account.getFirstName());
                System.out.println("Last Name: " + account.getLastName());
                System.out.println("Email: " + account.getEmail());
                System.out.println("Phone Number: " + account.getPhoneNumber());
                System.out.println("Nationality: " + account.getNationality());
                System.out.println("Date of Birth: " + account.getDateOfBirth());
                System.out.println("Passport Number: " + account.getPassportNumber());
                System.out.println("Active: " + (account.isActive() ? "Yes" : "No"));
                System.out.println("Registration Date: " + account.getRegistrationDate());
                System.out.println("Last Login Date: " + account.getLastLoginDate());
                return;
            }
        }
        System.out.println("Account with ID '" + accountId + "' not found in search results.");
    }
    
    /**
     * Executes the complete SearchTourist use case flow.
     */
    public void executeUseCase() {
        System.out.println("=== Welcome to ETOUR Tourist Search System ===");
        
        // Step 1: Agency operator must be logged in (Entry condition)
        if (!agencyLoggedIn) {
            simulateAgencyLogin();
            
            if (!agencyLoggedIn) {
                System.out.println("Exiting system: Login required to access tourist search functionality.");
                return;
            }
        }
        
        boolean continueSearching = true;
        
        while (continueSearching) {
            // Step 2: Show the search form (Flow of events step 2)
            System.out.println("\n--- Tourist Search ---");
            System.out.println("1. Search for tourist accounts");
            System.out.println("2. View all tourist accounts");
            System.out.println("3. Exit");
            System.out.print("Select option: ");
            
            String option = scanner.nextLine().trim();
            
            switch (option) {
                case "1":
                    // Step 3: Fill out the form and submit (Flow of events step 3)
                    SearchParameters searchParams = displaySearchForm();
                    
                    // Step 4: Processing the request (Flow of events step 4)
                    List<TouristAccount> results = processSearchRequest(searchParams);
                    
                    // Exit condition: System returns list of accounts that meet search criteria
                    displaySearchResults(results);
                    break;
                    
                case "2":
                    // View all accounts (no filtering)
                    System.out.println("\n=== Viewing All Tourist Accounts ===");
                    List<TouristAccount> allAccounts = touristService.getAllTouristAccounts();
                    displaySearchResults(allAccounts);
                    break;
                    
                case "3":
                    continueSearching = false;
                    System.out.println("Thank you for using ETOUR Tourist Search System. Goodbye!");
                    break;
                    
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            
            if (continueSearching && !option.equals("3")) {
                System.out.print("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
    }
    
    /**
     * Cleans up resources used by the use case.
     */
    public void cleanup() {
        if (scanner != null) {
            scanner.close();
        }
    }
    
    /**
     * Main method for standalone execution of the SearchTourist use case.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        SearchTouristUseCase useCase = new SearchTouristUseCase();
        
        try {
            useCase.executeUseCase();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            useCase.cleanup();
        }
    }
}