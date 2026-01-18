'''
A runnable Java program implementing the ViewRegistersList use case.
This program simulates a digital register system where an administrator
can view lists of registers filtered by academic year, with proper
authentication, error handling, and connection management.
'''
import java.util.*;
public class ViewRegistersList {
    /**
     * The Register class represents a single digital register entry,
     * containing details about the class and associated academic year.
     */
    static class Register {
        private String className;
        private String registerId;
        private String academicYear;
        public Register(String className, String registerId, String academicYear) {
            this.className = className;
            this.registerId = registerId;
            this.academicYear = academicYear;
        }
        public String getClassName() {
            return className;
        }
        public String getRegisterId() {
            return registerId;
        }
        public String getAcademicYear() {
            return academicYear;
        }
        @Override
        public String toString() {
            return "Class: " + className + " | Register ID: " + registerId + " | Academic Year: " + academicYear;
        }
    }
    /**
     * Simulates a database of digital registers.
     * In a real application, this class would connect to an actual database or server.
     */
    static class RegisterDatabase {
        private List<Register> registers;
        public RegisterDatabase() {
            registers = new ArrayList<>();
            // Sample data with registers for different academic years
            registers.add(new Register("Grade 10A", "REG001", "2023-2024"));
            registers.add(new Register("Grade 10B", "REG002", "2023-2024"));
            registers.add(new Register("Grade 11A", "REG003", "2023-2024"));
            registers.add(new Register("Grade 9A", "REG004", "2022-2023"));
            registers.add(new Register("Grade 9B", "REG005", "2022-2023"));
            registers.add(new Register("Grade 12A", "REG006", "2024-2025"));
        }
        /**
         * Retrieves all registers for a given academic year.
         * @param academicYear the academic year to filter by
         * @return a list of Register objects matching the year
         */
        public List<Register> getRegistersByAcademicYear(String academicYear) {
            List<Register> result = new ArrayList<>();
            for (Register register : registers) {
                if (register.getAcademicYear().equals(academicYear)) {
                    result.add(register);
                }
            }
            return result;
        }
        /**
         * Returns a set of all distinct academic years present in the database.
         * @return a Set of academic year strings
         */
        public Set<String> getAvailableAcademicYears() {
            Set<String> years = new HashSet<>();
            for (Register register : registers) {
                years.add(register.getAcademicYear());
            }
            return years;
        }
    }
    /**
     * Manages user authentication and administrator status.
     */
    static class AuthManager {
        private boolean isAdmin = false;
        /**
         * Simulates an administrator login.
         * A real implementation would validate credentials.
         */
        public void loginAsAdministrator() {
            System.out.println("Logging in as administrator...");
            isAdmin = true;
            System.out.println("Administrator login successful!");
        }
        public boolean isAdministrator() {
            return isAdmin;
        }
    }
    /**
     * The main system class that orchestrates the entire use case workflow.
     */
    static class DigitalRegisterSystem {
        private AuthManager authManager;
        private RegisterDatabase database;
        private Scanner scanner;
        public DigitalRegisterSystem() {
            authManager = new AuthManager();
            database = new RegisterDatabase();
            scanner = new Scanner(System.in);
        }
        /**
         * Implements the core ViewRegistersList functionality:
         * 1. Verifies administrator login.
         * 2. Displays academic years for selection.
         * 3. Retrieves and displays registers for the chosen year.
         * 4. Handles postconditions including connection interruption.
         */
        public void viewRegistersList() {
            if (!authManager.isAdministrator()) {
                System.out.println("Error: User is not logged in as administrator.");
                System.out.println("Please log in as administrator first.");
                return;
            }
            System.out.println("\n=== Digital Register System ===");
            System.out.println("Click on the 'Digital Register' button (simulated)");
            // Step 1: Show screen for academic year selection
            System.out.println("\nAvailable Academic Years:");
            Set<String> availableYears = database.getAvailableAcademicYears();
            List<String> yearList = new ArrayList<>(availableYears);
            for (int i = 0; i < yearList.size(); i++) {
                System.out.println((i + 1) + ". " + yearList.get(i));
            }
            // Step 2: User selects the school year with robust input validation
            boolean validSelection = false;
            String selectedYear = null;
            while (!validSelection) {
                System.out.print("\nSelect academic year (enter number, or 0 to cancel): ");
                String input = scanner.nextLine();
                try {
                    int choice = Integer.parseInt(input);
                    if (choice == 0) {
                        System.out.println("Operation cancelled by user.");
                        return;
                    }
                    if (choice >= 1 && choice <= yearList.size()) {
                        selectedYear = yearList.get(choice - 1);
                        validSelection = true;
                    } else {
                        System.out.println("Invalid selection. Please enter a number between 1 and " + yearList.size() + ".");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }
            System.out.println("\nSelected Academic Year: " + selectedYear);
            // Step 3: Search and display registers
            displayRegistersForYear(selectedYear);
            // Handle postconditions
            handlePostConditions();
        }
        /**
         * Displays all registers for a chosen academic year.
         * @param academicYear the year to display registers for
         */
        private void displayRegistersForYear(String academicYear) {
            System.out.println("\n=== Digital Registers for " + academicYear + " ===");
            List<Register> registers = database.getRegistersByAcademicYear(academicYear);
            if (registers.isEmpty()) {
                System.out.println("No digital registers found for the selected academic year.");
            } else {
                System.out.println("Total registers found: " + registers.size());
                System.out.println("----------------------------------------");
                for (Register register : registers) {
                    System.out.println(register);
                }
                System.out.println("----------------------------------------");
            }
        }
        /**
         * Simulates postconditions including potential SMOS server connection interruption.
         */
        private void handlePostConditions() {
            System.out.println("\nPostcondition: System is displaying the list of registers.");
            Random random = new Random();
            if (random.nextInt(10) < 2) { // 20% chance of interruption
                System.out.println("Warning: Connection to SMOS server interrupted.");
                System.out.println("Data displayed from local cache.");
            } else {
                System.out.println("Connection to SMOS server stable.");
            }
        }
        /**
         * Starts the system with administrator login and main interaction loop.
         */
        public void start() {
            System.out.println("=== Starting Digital Register System ===");
            authManager.loginAsAdministrator();
            boolean running = true;
            while (running) {
                viewRegistersList();
                System.out.print("\nWould you like to view registers for another year? (yes/no): ");
                String response = scanner.nextLine().toLowerCase();
                if (!response.equals("yes") && !response.equals("y")) {
                    running = false;
                    System.out.println("Thank you for using Digital Register System. Goodbye!");
                }
            }
            scanner.close();
        }
    }
    /**
     * Entry point of the program.
     * Creates and starts the DigitalRegisterSystem.
     */
    public static void main(String[] args) {
        DigitalRegisterSystem system = new DigitalRegisterSystem();
        system.start();
    }
}