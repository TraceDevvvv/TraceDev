import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.time.Year;
import java.util.InputMismatchException;

/**
 * Represents a Digital Register for a specific class and academic year.
 * A register contains metadata about a class's digital records.
 */
class Register {
    private String registerId;
    private String className;
    private int academicYearStart; // Starting year of the academic year (e.g., 2023 for 2023-2024)
    private String teacherName;
    private String subject;
    
    /**
     * Constructor to create a Register object.
     * @param registerId Unique identifier for the register.
     * @param className Name of the class (e.g., "10A", "11B").
     * @param academicYearStart Starting year of the academic year.
     * @param teacherName Name of the teacher responsible.
     * @param subject Subject of the register.
     */
    public Register(String registerId, String className, int academicYearStart, String teacherName, String subject) {
        this.registerId = registerId;
        this.className = className;
        this.academicYearStart = academicYearStart;
        this.teacherName = teacherName;
        this.subject = subject;
    }
    
    /**
     * Gets the register ID.
     * @return registerId.
     */
    public String getRegisterId() {
        return registerId;
    }
    
    /**
     * Gets the class name.
     * @return className.
     */
    public String getClassName() {
        return className;
    }
    
    /**
     * Gets the starting year of the academic year.
     * @return academicYearStart.
     */
    public int getAcademicYearStart() {
        return academicYearStart;
    }
    
    /**
     * Gets the teacher name.
     * @return teacherName.
     */
    public String getTeacherName() {
        return teacherName;
    }
    
    /**
     * Gets the subject.
     * @return subject.
     */
    public String getSubject() {
        return subject;
    }
    
    /**
     * Returns a string representation of the Register.
     * @return Formatted string with register details.
     */
    @Override
    public String toString() {
        return "Register ID: " + registerId + 
               " | Class: " + className + 
               " | Academic Year: " + academicYearStart + "-" + (academicYearStart + 1) +
               " | Teacher: " + teacherName + 
               " | Subject: " + subject;
    }
}

/**
 * Manages a collection of registers and provides operations to retrieve registers by academic year.
 * Simulates the archive of digital records.
 */
class ClassRegistration {
    private Map<Integer, List<Register>> registerArchive;
    
    /**
     * Constructor initializes the archive and populates it with sample data.
     */
    public ClassRegistration() {
        registerArchive = new HashMap<>();
        initializeSampleData();
    }
    
    /**
     * Populates the archive with sample registers for demonstration.
     * This simulates existing data in the system.
     */
    private void initializeSampleData() {
        // Add registers for academic year 2023-2024 (start year 2023)
        List<Register> year2023Registers = new ArrayList<>();
        year2023Registers.add(new Register("REG001", "10A", 2023, "Mr. Smith", "Mathematics"));
        year2023Registers.add(new Register("REG002", "10B", 2023, "Ms. Johnson", "Science"));
        year2023Registers.add(new Register("REG003", "11A", 2023, "Dr. Brown", "History"));
        year2023Registers.add(new Register("REG004", "10A", 2023, "Mrs. Davis", "English"));
        registerArchive.put(2023, year2023Registers);
        
        // Add registers for academic year 2022-2023 (start year 2022)
        List<Register> year2022Registers = new ArrayList<>();
        year2022Registers.add(new Register("REG101", "9A", 2022, "Mr. Wilson", "Mathematics"));
        year2022Registers.add(new Register("REG102", "9B", 2022, "Ms. Taylor", "Science"));
        year2022Registers.add(new Register("REG103", "10A", 2022, "Dr. Clark", "Physics"));
        registerArchive.put(2022, year2022Registers);
        
        // Add registers for academic year 2024-2025 (start year 2024)
        List<Register> year2024Registers = new ArrayList<>();
        year2024Registers.add(new Register("REG201", "11A", 2024, "Mr. Anderson", "Chemistry"));
        year2024Registers.add(new Register("REG202", "11B", 2024, "Ms. Roberts", "Biology"));
        registerArchive.put(2024, year2024Registers);
    }
    
    /**
     * Retrieves all registers for a specific academic year (starting year).
     * @param academicYearStart The starting year of the academic year (e.g., 2023 for 2023-2024).
     * @return List of Register objects for that academic year. Returns empty list if no registers found.
     */
    public List<Register> getRegistersByAcademicYear(int academicYearStart) {
        // Return registers for the year, or an empty list if year not found
        return registerArchive.getOrDefault(academicYearStart, new ArrayList<>());
    }
    
    /**
     * Checks if any registers exist for a given academic year.
     * @param academicYearStart The starting year to check.
     * @return true if registers exist, false otherwise.
     */
    public boolean hasRegistersForYear(int academicYearStart) {
        return registerArchive.containsKey(academicYearStart) && 
               !registerArchive.get(academicYearStart).isEmpty();
    }
    
    /**
     * Gets a list of all academic years that have registers.
     * @return List of academic year start years.
     */
    public List<Integer> getAvailableAcademicYears() {
        return new ArrayList<>(registerArchive.keySet());
    }
}

/**
 * Main application class that simulates the ViewRegistersList use case.
 * Handles user interaction and orchestrates the viewing of registers.
 */
public class ViewRegistersList {
    private ClassRegistration registrationSystem;
    private Scanner scanner;
    private boolean isConnectedToSMOS = true; // Simulates connection to SMOS server
    
    /**
     * Constructor initializes the system components.
     */
    public ViewRegistersList() {
        registrationSystem = new ClassRegistration();
        scanner = new Scanner(System.in);
    }
    
    /**
     * Simulates the administrator logging in.
     * In a real system, this would verify credentials.
     * @return true if login successful, false otherwise.
     */
    private boolean simulateAdminLogin() {
        System.out.println("=== Administrator Login Simulation ===");
        System.out.print("Enter administrator username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine(); // In real system, password would be hidden
        
        // Simple simulation - accept any non-empty credentials
        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            System.out.println("Login failed: Username and password cannot be empty.");
            return false;
        }
        
        System.out.println("Login successful as Administrator.");
        return true;
    }
    
    /**
     * Simulates clicking the "Digital Register" button.
     * This would be a UI action in a real system.
     */
    private void simulateDigitalRegisterButtonClick() {
        System.out.println("\n=== Digital Register System ===");
        System.out.println("Administrator clicked 'Digital Register' button.");
    }
    
    /**
     * Displays the screen for selecting an academic year.
     * Shows available academic years from the system.
     */
    private void showAcademicYearSelectionScreen() {
        System.out.println("\n=== Select Academic Year ===");
        List<Integer> availableYears = registrationSystem.getAvailableAcademicYears();
        
        if (availableYears.isEmpty()) {
            System.out.println("No academic years with registers available in the system.");
            return;
        }
        
        System.out.println("Available academic years:");
        for (int i = 0; i < availableYears.size(); i++) {
            int year = availableYears.get(i);
            System.out.println((i + 1) + ". " + year + "-" + (year + 1));
        }
        System.out.println("0. Exit");
    }
    
    /**
     * Prompts user to select an academic year and validates the input.
     * @return The selected academic year start, or -1 if invalid/exit.
     */
    private int getUserAcademicYearSelection() {
        List<Integer> availableYears = registrationSystem.getAvailableAcademicYears();
        
        while (true) {
            try {
                System.out.print("\nSelect academic year (enter number): ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                if (choice == 0) {
                    return -1; // Exit option
                }
                
                if (choice < 1 || choice > availableYears.size()) {
                    System.out.println("Invalid selection. Please choose a number between 1 and " + availableYears.size());
                    continue;
                }
                
                int selectedYear = availableYears.get(choice - 1);
                System.out.println("Selected academic year: " + selectedYear + "-" + (selectedYear + 1));
                return selectedYear;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
    
    /**
     * Searches and displays all digital registers for the selected academic year.
     * @param academicYearStart The starting year of the academic year to search.
     */
    private void searchAndDisplayRegisters(int academicYearStart) {
        System.out.println("\n=== Searching archive for digital records... ===");
        
        // Simulate search delay
        try {
            Thread.sleep(1000); // 1 second delay to simulate search
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        List<Register> registers = registrationSystem.getRegistersByAcademicYear(academicYearStart);
        
        if (registers.isEmpty()) {
            System.out.println("No digital registers found for academic year " + 
                             academicYearStart + "-" + (academicYearStart + 1));
            return;
        }
        
        System.out.println("\n=== Digital Registers for Academic Year " + 
                         academicYearStart + "-" + (academicYearStart + 1) + " ===");
        System.out.println("Total registers found: " + registers.size());
        System.out.println("--------------------------------------------------");
        
        // Group registers by class for better organization
        Map<String, List<Register>> registersByClass = new HashMap<>();
        for (Register register : registers) {
            String className = register.getClassName();
            registersByClass.putIfAbsent(className, new ArrayList<>());
            registersByClass.get(className).add(register);
        }
        
        // Display registers grouped by class
        for (Map.Entry<String, List<Register>> entry : registersByClass.entrySet()) {
            System.out.println("\nClass: " + entry.getKey());
            System.out.println("Registers:");
            for (Register register : entry.getValue()) {
                System.out.println("  - " + register);
            }
        }
        
        System.out.println("\n--------------------------------------------------");
        System.out.println("Display complete.");
    }
    
    /**
     * Simulates interruption of connection to SMOS server.
     * This is part of the postconditions.
     */
    private void interruptSMOSConnection() {
        System.out.println("\n=== SMOS Server Connection ===");
        if (isConnectedToSMOS) {
            isConnectedToSMOS = false;
            System.out.println("Administrator interrupted connection to SMOS server.");
        } else {
            System.out.println("Already disconnected from SMOS server.");
        }
    }
    
    /**
     * Main workflow that orchestrates the entire use case sequence.
     */
    public void executeUseCase() {
        System.out.println("=== View Registers List Use Case ===");
        
        // Precondition: User must be logged in as administrator
        if (!simulateAdminLogin()) {
            System.out.println("Use case cannot proceed without administrator login.");
            return;
        }
        
        // Simulate clicking the "Digital Register" button
        simulateDigitalRegisterButtonClick();
        
        // Step 1: System shows screen for selection of academic year
        showAcademicYearSelectionScreen();
        
        // Step 2: User selects the school year
        int selectedYear = getUserAcademicYearSelection();
        
        if (selectedYear == -1) {
            System.out.println("Exiting system.");
            return;
        }
        
        // Step 3: System searches archive and displays registers
        searchAndDisplayRegisters(selectedYear);
        
        // Postcondition: System displays list of registers
        System.out.println("\n=== Postcondition ===");
        System.out.println("System has displayed the list of registers for academic year " + 
                         selectedYear + "-" + (selectedYear + 1));
        
        // Postcondition: Administrator interrupts connection to SMOS server
        interruptSMOSConnection();
        
        System.out.println("\n=== Use Case Completed Successfully ===");
    }
    
    /**
     * Cleans up resources.
     */
    public void cleanup() {
        if (scanner != null) {
            scanner.close();
        }
    }
    
    /**
     * Main method - entry point of the program.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        ViewRegistersList app = new ViewRegistersList();
        
        try {
            app.executeUseCase();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            app.cleanup();
        }
    }
}