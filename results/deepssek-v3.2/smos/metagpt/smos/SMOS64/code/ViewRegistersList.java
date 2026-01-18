/*
 * ViewRegistersList.java
 * Complete implementation of the ViewRegistersList use case.
 * This program simulates viewing digital registers for an academic year by direction users.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents an academic year (e.g., "2023-2024")
 */
class AcademicYear {
    private final String year;
    private final List<Register> registers;
    
    public AcademicYear(String year) {
        if (year == null || year.trim().isEmpty()) {
            throw new IllegalArgumentException("Academic year cannot be null or empty");
        }
        this.year = year.trim();
        this.registers = new ArrayList<>();
    }
    
    public String getYear() {
        return year;
    }
    
    public void addRegister(Register register) {
        if (register == null) {
            throw new IllegalArgumentException("Register cannot be null");
        }
        if (!registers.contains(register)) {
            registers.add(register);
        }
    }
    
    public List<Register> getRegisters() {
        return new ArrayList<>(registers); // Return copy for immutability
    }
    
    public int getRegisterCount() {
        return registers.size();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AcademicYear other = (AcademicYear) obj;
        return year.equals(other.year);
    }
    
    @Override
    public int hashCode() {
        return year.hashCode();
    }
    
    @Override
    public String toString() {
        return "AcademicYear: " + year;
    }
}

/**
 * Represents a digital register associated with a specific class
 */
class Register {
    private final String registerId;
    private final String className;
    private final AcademicYear academicYear;
    private final String teacherName;
    
    public Register(String registerId, String className, AcademicYear academicYear, String teacherName) {
        if (registerId == null || registerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Register ID cannot be null or empty");
        }
        if (className == null || className.trim().isEmpty()) {
            throw new IllegalArgumentException("Class name cannot be null or empty");
        }
        if (academicYear == null) {
            throw new IllegalArgumentException("Academic year cannot be null");
        }
        if (teacherName == null || teacherName.trim().isEmpty()) {
            throw new IllegalArgumentException("Teacher name cannot be null or empty");
        }
        
        this.registerId = registerId.trim();
        this.className = className.trim();
        this.academicYear = academicYear;
        this.teacherName = teacherName.trim();
    }
    
    public String getRegisterId() {
        return registerId;
    }
    
    public String getClassName() {
        return className;
    }
    
    public AcademicYear getAcademicYear() {
        return academicYear;
    }
    
    public String getTeacherName() {
        return teacherName;
    }
    
    @Override
    public String toString() {
        return String.format("Register ID: %s | Class: %s | Teacher: %s", 
                           registerId, className, teacherName);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Register other = (Register) obj;
        return registerId.equals(other.registerId);
    }
    
    @Override
    public int hashCode() {
        return registerId.hashCode();
    }
}

/**
 * Represents the Direction user (administration)
 */
class Direction {
    private final String username;
    private boolean loggedIn;
    
    public Direction(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        this.username = username.trim();
        this.loggedIn = false;
    }
    
    public void login() {
        this.loggedIn = true;
        System.out.println("Direction user '" + username + "' logged in successfully.");
    }
    
    public void logout() {
        this.loggedIn = false;
        System.out.println("Direction user '" + username + "' logged out.");
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void clickDigitalRegisterButton() {
        if (!loggedIn) {
            throw new IllegalStateException("User must be logged in to click Digital Register button");
        }
        System.out.println("Direction user '" + username + "' clicked 'Digital Register' button.");
    }
}

/**
 * Manages the archive of academic years and registers
 */
class RegisterArchive {
    private final Map<AcademicYear, List<Register>> archive;
    
    public RegisterArchive() {
        this.archive = new HashMap<>();
        initializeSampleData();
    }
    
    /**
     * Initialize with sample data for demonstration
     */
    private void initializeSampleData() {
        // Create academic years
        AcademicYear year2022 = new AcademicYear("2022-2023");
        AcademicYear year2023 = new AcademicYear("2023-2024");
        AcademicYear year2024 = new AcademicYear("2024-2025");
        
        // Add registers for 2022-2023
        year2022.addRegister(new Register("REG001", "Class 10A", year2022, "Mr. Smith"));
        year2022.addRegister(new Register("REG002", "Class 10B", year2022, "Ms. Johnson"));
        year2022.addRegister(new Register("REG003", "Class 11A", year2022, "Dr. Williams"));
        
        // Add registers for 2023-2024
        year2023.addRegister(new Register("REG101", "Class 10A", year2023, "Mr. Smith"));
        year2023.addRegister(new Register("REG102", "Class 10B", year2023, "Ms. Johnson"));
        year2023.addRegister(new Register("REG103", "Class 11A", year2023, "Dr. Williams"));
        year2023.addRegister(new Register("REG104", "Class 11B", year2023, "Mrs. Davis"));
        
        // Add registers for 2024-2025
        year2024.addRegister(new Register("REG201", "Class 10A", year2024, "Mr. Smith"));
        year2024.addRegister(new Register("REG202", "Class 10B", year2024, "Ms. Johnson"));
        
        // Add to archive
        archive.put(year2022, year2022.getRegisters());
        archive.put(year2023, year2023.getRegisters());
        archive.put(year2024, year2024.getRegisters());
    }
    
    /**
     * Get all available academic years in the archive
     */
    public List<AcademicYear> getAvailableAcademicYears() {
        return new ArrayList<>(archive.keySet());
    }
    
    /**
     * Search for registers by academic year
     */
    public List<Register> searchRegistersByYear(AcademicYear academicYear) {
        if (academicYear == null) {
            throw new IllegalArgumentException("Academic year cannot be null");
        }
        
        // Find the AcademicYear object in archive (using equals/hashCode)
        for (AcademicYear year : archive.keySet()) {
            if (year.equals(academicYear)) {
                return new ArrayList<>(archive.get(year)); // Return copy
            }
        }
        
        // If year not found, return empty list
        return new ArrayList<>();
    }
    
    /**
     * Search for registers by year string
     */
    public List<Register> searchRegistersByYear(String yearString) {
        if (yearString == null || yearString.trim().isEmpty()) {
            throw new IllegalArgumentException("Year string cannot be null or empty");
        }
        
        AcademicYear searchYear = new AcademicYear(yearString.trim());
        return searchRegistersByYear(searchYear);
    }
    
    /**
     * Check if a year exists in the archive
     */
    public boolean hasAcademicYear(String yearString) {
        if (yearString == null || yearString.trim().isEmpty()) {
            return false;
        }
        
        AcademicYear searchYear = new AcademicYear(yearString.trim());
        return archive.containsKey(searchYear);
    }
}

/**
 * Main system controller for the ViewRegistersList use case
 */
class ViewRegistersListSystem {
    private final Direction directionUser;
    private final RegisterArchive archive;
    private final Scanner scanner;
    private final AtomicBoolean operationInterrupted;
    
    public ViewRegistersListSystem(String directionUsername) {
        if (directionUsername == null || directionUsername.trim().isEmpty()) {
            throw new IllegalArgumentException("Direction username cannot be null or empty");
        }
        
        this.directionUser = new Direction(directionUsername);
        this.archive = new RegisterArchive();
        this.scanner = new Scanner(System.in);
        this.operationInterrupted = new AtomicBoolean(false);
        
        // Add shutdown hook to handle interruption
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            operationInterrupted.set(true);
            System.out.println("\n[System] Operation interrupted by system shutdown.");
        }));
    }
    
    /**
     * Main workflow for the ViewRegistersList use case
     */
    public void executeWorkflow() {
        try {
            // Precondition: User must be logged in
            if (!directionUser.isLoggedIn()) {
                directionUser.login();
            }
            
            // Precondition: User clicks "Digital Register" button
            directionUser.clickDigitalRegisterButton();
            
            // Step 1: Show selection screen for academic year
            showYearSelectionScreen();
            
            if (operationInterrupted.get()) {
                System.out.println("[System] Operation was interrupted.");
                return;
            }
            
            // Step 2: Get user selection
            AcademicYear selectedYear = getUserYearSelection();
            
            if (selectedYear == null) {
                System.out.println("[System] No year selected. Operation cancelled.");
                return;
            }
            
            if (operationInterrupted.get()) {
                System.out.println("[System] Operation was interrupted.");
                return;
            }
            
            // Step 3: Search and display registers
            searchAndDisplayRegisters(selectedYear);
            
        } catch (Exception e) {
            System.err.println("[System Error] An error occurred: " + e.getMessage());
            System.err.println("Operation failed. Please try again.");
        } finally {
            // Clean up
            if (directionUser.isLoggedIn()) {
                directionUser.logout();
            }
            scanner.close();
        }
    }
    
    /**
     * Display available academic years for selection
     */
    private void showYearSelectionScreen() {
        System.out.println("\n=== DIGITAL REGISTER SYSTEM ===");
        System.out.println("Available Academic Years:");
        
        List<AcademicYear> availableYears = archive.getAvailableAcademicYears();
        
        if (availableYears.isEmpty()) {
            System.out.println("No academic years available in the archive.");
            return;
        }
        
        for (int i = 0; i < availableYears.size(); i++) {
            System.out.println((i + 1) + ". " + availableYears.get(i).getYear());
        }
        System.out.println("0. Exit");
        System.out.println("-------------------------------");
    }
    
    /**
     * Get user's academic year selection
     */
    private AcademicYear getUserYearSelection() {
        List<AcademicYear> availableYears = archive.getAvailableAcademicYears();
        
        if (availableYears.isEmpty()) {
            return null;
        }
        
        System.out.print("Select academic year (enter number): ");
        
        // Check for interruption
        if (operationInterrupted.get()) {
            return null;
        }
        
        // Handle input safely
        String input = "";
        try {
            input = scanner.nextLine().trim();
            
            // Check for interruption during input
            if (operationInterrupted.get()) {
                return null;
            }
            
            if (input.equalsIgnoreCase("exit") || input.equals("0")) {
                System.out.println("Exiting year selection.");
                return null;
            }
            
            int choice = Integer.parseInt(input);
            
            if (choice < 1 || choice > availableYears.size()) {
                System.out.println("Invalid selection. Please try again.");
                return getUserYearSelection(); // Recursive retry
            }
            
            return availableYears.get(choice - 1);
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid input '" + input + "'. Please enter a number.");
            return getUserYearSelection(); // Recursive retry
        } catch (Exception e) {
            System.out.println("Error processing input: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Search for registers and display results
     */
    private void searchAndDisplayRegisters(AcademicYear academicYear) {
        System.out.println("\n=== SEARCHING REGISTERS FOR " + academicYear.getYear() + " ===");
        
        // Simulate searching in archive
        System.out.println("Searching in archive...");
        
        // Add small delay to simulate processing
        try {
            Thread.sleep(500);
            
            // Check for interruption during search
            if (operationInterrupted.get()) {
                System.out.println("[System] Search interrupted.");
                return;
            }
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("[System] Search interrupted.");
            return;
        }
        
        // Get registers from archive
        List<Register> registers = archive.searchRegistersByYear(academicYear);
        
        // Display results
        displayRegisters(registers, academicYear);
    }
    
    /**
     * Display the list of registers
     */
    private void displayRegisters(List<Register> registers, AcademicYear academicYear) {
        System.out.println("\n=== DIGITAL REGISTERS FOR " + academicYear.getYear() + " ===");
        
        if (registers.isEmpty()) {
            System.out.println("No digital registers found for the selected academic year.");
            return;
        }
        
        System.out.println("Total registers found: " + registers.size());
        System.out.println("\nList of registers (associated with class):");
        System.out.println("------------------------------------------");
        
        for (int i = 0; i < registers.size(); i++) {
            Register register = registers.get(i);
            System.out.println((i + 1) + ". " + register.toString());
        }
        
        System.out.println("------------------------------------------");
        System.out.println("End of list.");
        
        // Postcondition: System is viewing the list of registers
        System.out.println("\n[System] Successfully viewing list of registers for academic year: " + academicYear.getYear());
    }
    
    /**
     * Check if server connection is interrupted
     */
    public boolean isServerConnectionInterrupted() {
        // Simulate checking server connection
        return operationInterrupted.get();
    }
    
    /**
     * Interrupt the current operation (simulate connection interruption)
     */
    public void interruptOperation() {
        operationInterrupted.set(true);
        System.out.println("[System] Operation interrupted manually.");
    }
}

/**
 * Main class to run the ViewRegistersList program
 */
public class ViewRegistersList {
    
    /**
     * Main method - entry point of the application
     */
    public static void main(String[] args) {
        System.out.println("=== VIEW REGISTERS LIST SYSTEM ===\n");
        
        // Create and run the system
        ViewRegistersListSystem system = new ViewRegistersListSystem("admin_direction");
        
        // Optional: Simulate server interruption after 10 seconds (for demonstration)
        Thread interruptThread = new Thread(() -> {
            try {
                Thread.sleep(10000); // Wait 10 seconds
                if (!system.isServerConnectionInterrupted()) {
                    System.out.println("\n[Demo] Simulating SMOS server connection interruption...");
                    system.interruptOperation();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        interruptThread.setDaemon(true);
        interruptThread.start();
        
        // Execute the main workflow
        system.executeWorkflow();
        
        System.out.println("\n=== END OF PROGRAM ===");
    }
}