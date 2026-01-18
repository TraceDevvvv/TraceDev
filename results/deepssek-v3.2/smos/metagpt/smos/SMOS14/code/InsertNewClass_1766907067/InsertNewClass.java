import java.util.*;
import java.time.Year;

/**
 * InsertNewClass - A program to handle inserting a new class into the archive
 * This program simulates the use case where an administrator adds a new class.
 */
public class InsertNewClass {
    
    // Simple database simulation using a list
    private static List<SchoolClass> classArchive = new ArrayList<>();
    
    // Represents a school class with name, address, and academic year
    static class SchoolClass {
        private String name;
        private String address;
        private int academicYear;
        
        public SchoolClass(String name, String address, int academicYear) {
            this.name = name;
            this.address = address;
            this.academicYear = academicYear;
        }
        
        // Getters
        public String getName() { return name; }
        public String getAddress() { return address; }
        public int getAcademicYear() { return academicYear; }
        
        @Override
        public String toString() {
            return "Class: " + name + ", Address: " + address + ", Academic Year: " + academicYear;
        }
    }
    
    /**
     * Validates the input data for a new class
     * @param name The class name
     * @param address The class address
     * @param academicYear The academic year
     * @return A list of validation errors (empty if valid)
     */
    private static List<String> validateClassData(String name, String address, String academicYearStr) {
        List<String> errors = new ArrayList<>();
        
        // Validate name
        if (name == null || name.trim().isEmpty()) {
            errors.add("Class name cannot be empty");
        } else if (name.length() > 100) {
            errors.add("Class name cannot exceed 100 characters");
        }
        
        // Validate address
        if (address == null || address.trim().isEmpty()) {
            errors.add("Address cannot be empty");
        } else if (address.length() > 200) {
            errors.add("Address cannot exceed 200 characters");
        }
        
        // Validate academic year
        if (academicYearStr == null || academicYearStr.trim().isEmpty()) {
            errors.add("Academic year cannot be empty");
        } else {
            try {
                int academicYear = Integer.parseInt(academicYearStr);
                int currentYear = Year.now().getValue();
                
                // Academic year should be reasonable (not too far in past or future)
                if (academicYear < 2000 || academicYear > currentYear + 5) {
                    errors.add("Academic year must be between 2000 and " + (currentYear + 5));
                }
            } catch (NumberFormatException e) {
                errors.add("Academic year must be a valid number");
            }
        }
        
        return errors;
    }
    
    /**
     * Checks if a class with the same name and academic year already exists
     * @param name The class name to check
     * @param academicYear The academic year to check
     * @return true if duplicate found, false otherwise
     */
    private static boolean isDuplicateClass(String name, int academicYear) {
        for (SchoolClass schoolClass : classArchive) {
            if (schoolClass.getName().equalsIgnoreCase(name) && 
                schoolClass.getAcademicYear() == academicYear) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Inserts a new class into the archive after validation
     * @param name The class name
     * @param address The class address
     * @param academicYear The academic year
     * @return true if insertion successful, false otherwise
     */
    private static boolean insertNewClass(String name, String address, int academicYear) {
        // Simulate connection to SMOS server with random chance of interruption
        Random random = new Random();
        if (random.nextInt(100) < 10) { // 10% chance of server interruption
            System.out.println("ERROR: Connection to the SMOS server interrupted!");
            return false;
        }
        
        // Check for duplicates
        if (isDuplicateClass(name, academicYear)) {
            System.out.println("ERROR: A class with the same name already exists for this academic year!");
            return false;
        }
        
        // Create and add the new class
        try {
            SchoolClass newClass = new SchoolClass(name, address, academicYear);
            classArchive.add(newClass);
            System.out.println("SUCCESS: New class added to archive!");
            System.out.println("Details: " + newClass);
            return true;
        } catch (Exception e) {
            System.out.println("ERROR: Failed to save class due to system error: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Displays the current list of classes in the archive
     */
    private static void displayClassArchive() {
        if (classArchive.isEmpty()) {
            System.out.println("No classes in archive yet.");
        } else {
            System.out.println("\n=== CURRENT CLASS ARCHIVE ===");
            for (int i = 0; i < classArchive.size(); i++) {
                System.out.println((i + 1) + ". " + classArchive.get(i));
            }
            System.out.println("=============================\n");
        }
    }
    
    /**
     * Handles the data error use case ("Errodati")
     * @param errors List of validation errors
     */
    private static void handleDataErrors(List<String> errors) {
        System.out.println("\n=== DATA VALIDATION ERRORS ===");
        for (String error : errors) {
            System.out.println("- " + error);
        }
        System.out.println("Please correct the errors and try again.");
        System.out.println("===============================\n");
    }
    
    /**
     * Main method - simulates the InsertNewClass workflow
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== SCHOOL CLASS MANAGEMENT SYSTEM ===");
        System.out.println("Role: Administrator");
        System.out.println("Use Case: Insert New Class\n");
        
        // Simulate preconditions: user has already viewed classes
        System.out.println("Simulating preconditions:");
        System.out.println("- User is logged in as Administrator ✓");
        System.out.println("- User has viewed class list for academic year ✓");
        System.out.println("- User clicked 'New Class' button ✓\n");
        
        boolean continueAdding = true;
        
        while (continueAdding) {
            System.out.println("=== NEW CLASS FORM ===");
            
            // Step 1: System shows form (simulated with user input)
            System.out.print("Enter Class Name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter Class Address: ");
            String address = scanner.nextLine();
            
            System.out.print("Enter Academic Year: ");
            String academicYearStr = scanner.nextLine();
            
            // Step 4: Make checks on validity of data
            List<String> validationErrors = validateClassData(name, address, academicYearStr);
            
            if (!validationErrors.isEmpty()) {
                // Activate "Errodati" use case (data error handling)
                handleDataErrors(validationErrors);
                
                // Option to retry or exit
                System.out.print("Do you want to try again? (yes/no): ");
                String retry = scanner.nextLine();
                if (!retry.equalsIgnoreCase("yes")) {
                    System.out.println("Operation interrupted by administrator.");
                    break;
                }
                continue;
            }
            
            // If data is valid, proceed with insertion
            int academicYear = Integer.parseInt(academicYearStr);
            
            System.out.println("\nValidating and inserting class...");
            System.out.println("Connecting to SMOS server...");
            
            // Insert the new class
            boolean success = insertNewClass(name, address, academicYear);
            
            if (success) {
                displayClassArchive();
            }
            
            // Check if user wants to add another class
            System.out.print("\nDo you want to add another class? (yes/no): ");
            String another = scanner.nextLine();
            if (!another.equalsIgnoreCase("yes")) {
                continueAdding = false;
                System.out.println("Exiting system. Goodbye!");
            }
        }
        
        scanner.close();
    }
}