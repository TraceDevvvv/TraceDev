package com.culturalheritage;

import com.culturalheritage.database.DatabaseConnection;
import com.culturalheritage.repository.CulturalHeritageRepository;
import com.culturalheritage.service.CulturalHeritageService;
import com.culturalheritage.controller.CulturalHeritageController;
import com.culturalheritage.view.ConsoleUI;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Main class for the Cultural Heritage Management System.
 * This class initializes all components and starts the application.
 * It follows the system design specifications and implements the
 * InsertCulturalHeritage use case with proper component initialization.
 */
public class Main {
    
    /**
     * Main entry point for the Cultural Heritage Management System.
     * Initializes database connection, creates all components, and starts the UI.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== Cultural Heritage Management System ===");
        System.out.println("Initializing system components...\n");
        
        DatabaseConnection dbConnection = null;
        Connection connection = null;
        
        try {
            // Step 1: Initialize database connection
            System.out.println("1. Setting up database connection...");
            dbConnection = DatabaseConnection.getInstance();
            connection = dbConnection.getConnection();
            
            if (connection == null || connection.isClosed()) {
                throw new SQLException("Failed to establish database connection");
            }
            System.out.println("   ✓ Database connection established\n");
            
            // Step 2: Create repository and initialize database table
            System.out.println("2. Initializing Cultural Heritage Repository...");
            CulturalHeritageRepository repository = new CulturalHeritageRepository(connection);
            repository.createTable();
            System.out.println("   ✓ Repository initialized and table created\n");
            
            // Step 3: Create service layer
            System.out.println("3. Initializing Cultural Heritage Service...");
            CulturalHeritageService service = new CulturalHeritageService(repository);
            System.out.println("   ✓ Service layer initialized\n");
            
            // Step 4: Create controller
            System.out.println("4. Initializing Cultural Heritage Controller...");
            CulturalHeritageController controller = new CulturalHeritageController(service);
            System.out.println("   ✓ Controller initialized\n");
            
            // Step 5: Create and start Console UI
            System.out.println("5. Starting User Interface...");
            ConsoleUI consoleUI = new ConsoleUI(controller);
            System.out.println("   ✓ UI initialized\n");
            
            System.out.println("=== System Initialization Complete ===\n");
            System.out.println("Starting Cultural Heritage Management System...\n");
            
            // Start the application
            consoleUI.start();
            
        } catch (SQLException e) {
            System.err.println("\n=== FATAL ERROR ===");
            System.err.println("Database connection failed: " + e.getMessage());
            System.err.println("Please ensure:");
            System.err.println("1. H2 database driver is available in classpath");
            System.err.println("2. Database server is running (if using remote database)");
            System.err.println("3. Connection parameters are correct");
            System.err.println("\nApplication cannot continue without database connection.");
            System.exit(1);
        } catch (Exception e) {
            System.err.println("\n=== FATAL ERROR ===");
            System.err.println("Failed to initialize system: " + e.getMessage());
            e.printStackTrace();
            System.err.println("\nApplication cannot continue due to initialization error.");
            System.exit(1);
        } finally {
            // Clean up database connection
            if (dbConnection != null) {
                try {
                    System.out.println("\nCleaning up database resources...");
                    dbConnection.closeConnection();
                    System.out.println("Database connection closed successfully.");
                } catch (SQLException e) {
                    System.err.println("Warning: Failed to close database connection: " + e.getMessage());
                }
            }
        }
        
        System.out.println("\n=== Cultural Heritage Management System Shutdown Complete ===");
    }
    
    /**
     * Helper method to display system information.
     */
    private static void displaySystemInfo() {
        System.out.println("\nSystem Information:");
        System.out.println("-------------------");
        System.out.println("Application: Cultural Heritage Management System");
        System.out.println("Version: 1.0.0");
        System.out.println("Use Case: InsertCulturalHeritage");
        System.out.println("Database: H2 In-Memory");
        System.out.println("Architecture: MVC Pattern");
        System.out.println("Quality Requirement: No duplicate cultural heritage accepted");
        System.out.println("-------------------\n");
    }
}