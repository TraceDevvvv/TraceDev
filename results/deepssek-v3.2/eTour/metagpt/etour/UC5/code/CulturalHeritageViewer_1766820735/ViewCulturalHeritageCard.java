import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * ViewCulturalHeritageCard - Complete Java Program
 * 
 * This program demonstrates the "ViewCulturalHeritageCard" use case where an
 * agency operator can view details of a selected cultural heritage item.
 * The program simulates:
 * 1. Viewing a list of cultural goods (from SearchCulturalHeritage use case)
 * 2. Selecting an item to view its detailed card
 * 3. Loading data for the selected cultural heritage item
 * 4. Handling server connection interruptions (ETOUR server)
 * 
 * Entry condition: The agency has logged in (simulated with simple authentication)
 * Exit condition: The system displays the details of the selected cultural heritage
 */

/**
 * Represents a Cultural Heritage item with its properties
 */
class CulturalHeritage {
    private int id;
    private String name;
    private String type; // e.g., "Monument", "Artifact", "Document"
    private String location;
    private String description;
    private int year;
    private String status; // e.g., "Protected", "Endangered", "Well-maintained"
    
    public CulturalHeritage(int id, String name, String type, String location, 
                           String description, int year, String status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.location = location;
        this.description = description;
        this.year = year;
        this.status = status;
    }
    
    // Getters for all properties
    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public int getYear() { return year; }
    public String getStatus() { return status; }
    
    /**
     * Display the cultural heritage card with all details
     */
    public void displayCard() {
        System.out.println("\n=== CULTURAL HERITAGE CARD ===");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Location: " + location);
        System.out.println("Year: " + year);
        System.out.println("Status: " + status);
        System.out.println("Description: " + description);
        System.out.println("==============================\n");
    }
    
    /**
     * Display a brief summary for list view
     */
    public void displaySummary() {
        System.out.println(id + ". " + name + " (" + type + ", " + location + ")");
    }
}

/**
 * Service class that simulates data loading from ETOUR server
 * Handles server connection and data retrieval operations
 */
class CulturalHeritageService {
    // Simulated database of cultural heritage items
    private List<CulturalHeritage> culturalHeritageList;
    
    public CulturalHeritageService() {
        initializeSampleData();
    }
    
    /**
     * Initialize sample cultural heritage data
     * In a real application, this would come from a database or external service
     */
    private void initializeSampleData() {
        culturalHeritageList = new ArrayList<>();
        
        // Sample data representing cultural heritage items
        culturalHeritageList.add(new CulturalHeritage(1, "Colosseum", "Monument", 
            "Rome, Italy", "An elliptical amphitheatre in the centre of the city of Rome, Italy.", 
            80, "Protected"));
        
        culturalHeritageList.add(new CulturalHeritage(2, "Taj Mahal", "Monument", 
            "Agra, India", "An ivory-white marble mausoleum on the right bank of the river Yamuna.", 
            1653, "Protected"));
        
        culturalHeritageList.add(new CulturalHeritage(3, "Great Wall of China", "Monument", 
            "Northern China", "Series of fortifications made of stone, brick, tamped earth, wood.", 
            -700, "Protected"));
        
        culturalHeritageList.add(new CulturalHeritage(4, "Mona Lisa", "Artifact", 
            "Paris, France", "Portrait painting by Italian artist Leonardo da Vinci.", 
            1503, "Well-maintained"));
        
        culturalHeritageList.add(new CulturalHeritage(5, "Dead Sea Scrolls", "Document", 
            "Jerusalem, Israel", "Ancient Jewish religious manuscripts found in the Qumran Caves.", 
            -300, "Endangered"));
    }
    
    /**
     * Get all cultural heritage items (simulating SearchCulturalHeritage use case result)
     * @return List of all cultural heritage items
     */
    public List<CulturalHeritage> getAllCulturalHeritage() {
        return culturalHeritageList;
    }
    
    /**
     * Load data for a specific cultural heritage item by ID
     * Simulates server connection and handles potential interruptions
     * @param id The ID of the cultural heritage item to load
     * @return The CulturalHeritage object if found, null otherwise
     * @throws ServerConnectionException if connection to ETOUR server fails
     */
    public CulturalHeritage loadCulturalHeritageById(int id) throws ServerConnectionException {
        // Simulate server connection delay
        simulateServerConnection();
        
        // Check for random server interruption (simulating ETOUR server issues)
        if (isServerConnectionInterrupted()) {
            throw new ServerConnectionException("Connection to ETOUR server interrupted. Please try again.");
        }
        
        // Search for the cultural heritage item by ID
        for (CulturalHeritage item : culturalHeritageList) {
            if (item.getId() == id) {
                return item;
            }
        }
        
        return null; // Item not found
    }
    
    /**
     * Simulate server connection delay (like network latency)
     */
    private void simulateServerConnection() {
        try {
            System.out.print("Connecting to ETOUR server");
            for (int i = 0; i < 3; i++) {
                System.out.print(".");
                TimeUnit.MILLISECONDS.sleep(500);
            }
            System.out.println(" Connected!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(" Connection interrupted!");
        }
    }
    
    /**
     * Simulate random server connection interruption (10% chance)
     * @return true if connection is interrupted, false otherwise
     */
    private boolean isServerConnectionInterrupted() {
        // 10% chance of server interruption for simulation purposes
        return Math.random() < 0.1;
    }
    
    /**
     * Get a cultural heritage item by ID without server simulation (for list display)
     * @param id The ID of the cultural heritage item
     * @return The CulturalHeritage object if found, null otherwise
     */
    public CulturalHeritage getCulturalHeritageById(int id) {
        for (CulturalHeritage item : culturalHeritageList) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}

/**
 * Custom exception for server connection errors
 */
class ServerConnectionException extends Exception {
    public ServerConnectionException(String message) {
        super(message);
    }
}

/**
 * Main class that implements the ViewCulturalHeritageCard use case
 * Simulates the agency operator workflow
 */
public class ViewCulturalHeritageCard {
    private CulturalHeritageService heritageService;
    private Scanner scanner;
    private boolean isLoggedIn;
    
    public ViewCulturalHeritageCard() {
        this.heritageService = new CulturalHeritageService();
        this.scanner = new Scanner(System.in);
        this.isLoggedIn = false;
    }
    
    /**
     * Main entry point of the program
     */
    public static void main(String[] args) {
        ViewCulturalHeritageCard app = new ViewCulturalHeritageCard();
        app.run();
    }
    
    /**
     * Run the main application workflow
     */
    public void run() {
        System.out.println("=== CULTURAL HERITAGE MANAGEMENT SYSTEM ===\n");
        
        // Entry condition: Agency must be logged in
        if (!login()) {
            System.out.println("Login failed. Exiting system.");
            return;
        }
        
        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            int choice = getMenuChoice(1, 3);
            
            switch (choice) {
                case 1:
                    viewCulturalHeritageList();
                    break;
                case 2:
                    viewCulturalHeritageCard();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Thank you for using Cultural Heritage Management System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Simulate agency login (Entry Operator condition: The agency has logged)
     * @return true if login successful, false otherwise
     */
    private boolean login() {
        System.out.println("=== AGENCY LOGIN ===");
        System.out.print("Enter agency ID: ");
        String agencyId = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        // Simple authentication simulation
        // In a real system, this would validate against a database
        if (agencyId.equals("agency123") && password.equals("heritage2024")) {
            isLoggedIn = true;
            System.out.println("Login successful! Welcome, Agency Operator.\n");
            return true;
        } else {
            System.out.println("Invalid credentials. Please try again.");
            return false;
        }
    }
    
    /**
     * Display the main menu options
     */
    private void displayMainMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. View Cultural Heritage List (SearchCulturalHeritage)");
        System.out.println("2. View Cultural Heritage Card");
        System.out.println("3. Exit");
        System.out.print("Enter your choice (1-3): ");
    }
    
    /**
     * Get and validate menu choice from user
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
                    System.out.print("Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
    
    /**
     * Step 1: View the list of cultural goods as a result of SearchCulturalHeritage use case
     * This corresponds to the first step in the flow of events
     */
    private void viewCulturalHeritageList() {
        System.out.println("\n=== CULTURAL HERITAGE LIST ===");
        System.out.println("(Result of SearchCulturalHeritage use case)");
        System.out.println("-------------------------------------------");
        
        List<CulturalHeritage> heritageList = heritageService.getAllCulturalHeritage();
        
        if (heritageList.isEmpty()) {
            System.out.println("No cultural heritage items found.");
        } else {
            for (CulturalHeritage item : heritageList) {
                item.displaySummary();
            }
        }
        System.out.println("-------------------------------------------");
        System.out.println("Total items: " + heritageList.size());
        System.out.println("(Select an item by ID in the next menu to view its card)\n");
    }
    
    /**
     * Step 2: Load and display data for the selected cultural heritage item
     * This implements the main ViewCulturalHeritageCard functionality
     */
    private void viewCulturalHeritageCard() {
        System.out.println("\n=== VIEW CULTURAL HERITAGE CARD ===");
        
        // First, show the list so user can see what's available
        List<CulturalHeritage> heritageList = heritageService.getAllCulturalHeritage();
        if (heritageList.isEmpty()) {
            System.out.println("No cultural heritage items available.");
            return;
        }
        
        System.out.println("Available cultural heritage items:");
        for (CulturalHeritage item : heritageList) {
            item.displaySummary();
        }
        
        // Get user selection
        System.out.print("\nEnter the ID of the cultural heritage item to view: ");
        int selectedId;
        try {
            selectedId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a number.");
            return;
        }
        
        // Validate ID exists
        CulturalHeritage selectedItem = heritageService.getCulturalHeritageById(selectedId);
        if (selectedItem == null) {
            System.out.println("Cultural heritage item with ID " + selectedId + " not found.");
            return;
        }
        
        System.out.println("\nLoading data for: " + selectedItem.getName());
        
        try {
            // Step 2: Load the data for the selected cultural heritage item
            // This simulates loading from server with potential connection issues
            CulturalHeritage loadedItem = heritageService.loadCulturalHeritageById(selectedId);
            
            if (loadedItem != null) {
                // Exit condition: Display the details of the selected cultural heritage
                loadedItem.displayCard();
                System.out.println("Cultural heritage card displayed successfully.");
            } else {
                System.out.println("Error: Could not load data for the selected item.");
            }
            
        } catch (ServerConnectionException e) {
            // Handle interruption of the connection to the server ETOUR
            System.out.println("\nERROR: " + e.getMessage());
            System.out.println("Please check your internet connection and try again.");
            System.out.println("You can still view cached data:");
            selectedItem.displayCard();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}