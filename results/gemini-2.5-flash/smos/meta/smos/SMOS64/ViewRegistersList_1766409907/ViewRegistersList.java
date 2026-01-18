import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.stream.Collectors;

/**
 * Custom exception for SMOS server-related issues.
 * This helps in handling specific server connection problems.
 */
class SMOSServerException extends Exception {
    public SMOSServerException(String message) {
        super(message);
    }
}

/**
 * Represents an academic year, typically defined by a start and end year.
 */
class AcademicYear {
    private final int startYear;
    private final int endYear;

    public AcademicYear(int startYear, int endYear) {
        if (startYear <= 0 || endYear <= 0 || startYear >= endYear) {
            throw new IllegalArgumentException("Invalid academic year: startYear must be less than endYear and positive.");
        }
        if (startYear + 1 != endYear) { // Academic years typically span two calendar years, e.g., 2023-2024
            throw new IllegalArgumentException("Invalid academic year format: endYear must be startYear + 1.");
        }
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public int getStartYear() {
        return startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    @Override
    public String toString() {
        return startYear + "-" + endYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicYear that = (AcademicYear) o;
        return startYear == that.startYear && endYear == that.endYear;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startYear, endYear);
    }
}

/**
 * Represents a class within the academic system (e.g., "1A", "2B").
 */
class ClassInfo {
    private final String className;

    public ClassInfo(String className) {
        if (className == null || className.trim().isEmpty()) {
            throw new IllegalArgumentException("Class name cannot be null or empty.");
        }
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return className;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassInfo classInfo = (ClassInfo) o;
        return className.equals(classInfo.className);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className);
    }
}

/**
 * Represents a digital register, associated with a specific academic year and class.
 */
class DigitalRegister {
    private final String registerId;
    private final AcademicYear academicYear;
    private final ClassInfo classInfo;
    private final String contentSummary; // A summary of the register's content

    public DigitalRegister(String registerId, AcademicYear academicYear, ClassInfo classInfo, String contentSummary) {
        if (registerId == null || registerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Register ID cannot be null or empty.");
        }
        if (academicYear == null) {
            throw new IllegalArgumentException("Academic year cannot be null.");
        }
        if (classInfo == null) {
            throw new IllegalArgumentException("Class info cannot be null.");
        }
        this.registerId = registerId;
        this.academicYear = academicYear;
        this.classInfo = classInfo;
        this.contentSummary = contentSummary != null ? contentSummary : "No content summary available.";
    }

    public String getRegisterId() {
        return registerId;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public ClassInfo getClassInfo() {
        return classInfo;
    }

    public String getContentSummary() {
        return contentSummary;
    }

    @Override
    public String toString() {
        return String.format("Register ID: %s, Class: %s, Year: %s, Summary: %s",
                registerId, classInfo.getClassName(), academicYear.toString(), contentSummary);
    }
}

/**
 * Simulates the SMOS (School Management and Operations System) server.
 * It provides methods to retrieve digital registers and simulates connection status.
 */
class SMOSServer {
    private List<DigitalRegister> registers;
    private boolean isServerConnected;

    public SMOSServer() {
        this.isServerConnected = true; // Server starts as connected
        initializeSampleData();
    }

    /**
     * Initializes some sample digital register data for demonstration.
     */
    private void initializeSampleData() {
        registers = new ArrayList<>();
        AcademicYear year2022_2023 = new AcademicYear(2022, 2023);
        AcademicYear year2023_2024 = new AcademicYear(2023, 2024);
        AcademicYear year2024_2025 = new AcademicYear(2024, 2025);

        ClassInfo class1A = new ClassInfo("1A");
        ClassInfo class2B = new ClassInfo("2B");
        ClassInfo class3C = new ClassInfo("3C");
        ClassInfo class4D = new ClassInfo("4D");

        registers.add(new DigitalRegister("REG-001-22-23-1A", year2022_2023, class1A, "Attendance and grades for Q1"));
        registers.add(new DigitalRegister("REG-002-22-23-2B", year2022_2023, class2B, "Lesson plans for Math and Science"));
        registers.add(new DigitalRegister("REG-003-23-24-1A", year2023_2024, class1A, "Attendance records for September"));
        registers.add(new DigitalRegister("REG-004-23-24-2B", year2023_2024, class2B, "Student performance reports"));
        registers.add(new DigitalRegister("REG-005-23-24-3C", year2023_2024, class3C, "Teacher notes and observations"));
        registers.add(new DigitalRegister("REG-006-24-25-4D", year2024_2025, class4D, "New curriculum implementation details"));
        registers.add(new DigitalRegister("REG-007-23-24-1A", year2023_2024, class1A, "Homework assignments for October"));
    }

    /**
     * Simulates connecting to the SMOS server.
     */
    public void connect() {
        System.out.println("Attempting to connect to SMOS server...");
        this.isServerConnected = true;
        System.out.println("SMOS server connected.");
    }

    /**
     * Simulates disconnecting from the SMOS server.
     */
    public void disconnect() {
        System.out.println("Disconnecting from SMOS server...");
        this.isServerConnected = false;
        System.out.println("SMOS server disconnected.");
    }

    /**
     * Checks if the SMOS server is currently connected.
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        return isServerConnected;
    }

    /**
     * Retrieves a list of digital registers for a given academic year.
     *
     * @param academicYear The academic year to search for.
     * @return A list of DigitalRegister objects.
     * @throws SMOSServerException If the server is not connected.
     */
    public List<DigitalRegister> getDigitalRegistersByAcademicYear(AcademicYear academicYear) throws SMOSServerException {
        if (!isServerConnected) {
            throw new SMOSServerException("Connection to the SMOS server interrupted.");
        }
        if (academicYear == null) {
            throw new IllegalArgumentException("Academic year cannot be null for search.");
        }

        // Simulate database query and filtering
        return registers.stream()
                .filter(reg -> reg.getAcademicYear().equals(academicYear))
                .collect(Collectors.toList());
    }
}

/**
 * Main application class for the ViewRegistersList use case.
 * This class orchestrates the user interaction, data retrieval, and display.
 */
public class ViewRegistersList {

    private static final String DIRECTION_USERNAME = "direction";
    private static final String DIRECTION_PASSWORD = "password"; // In a real app, this would be hashed

    /**
     * Simulates the user login process.
     * In a real application, this would involve actual authentication.
     *
     * @param scanner Scanner for user input.
     * @return true if login is successful, false otherwise.
     */
    private static boolean simulateLogin(Scanner scanner) {
        System.out.println("--- Login ---");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (DIRECTION_USERNAME.equals(username) && DIRECTION_PASSWORD.equals(password)) {
            System.out.println("Login successful as Direction.");
            return true;
        } else {
            System.out.println("Login failed. Invalid credentials.");
            return false;
        }
    }

    /**
     * Simulates the user clicking the "Digital Register" button.
     * This is a placeholder for a UI event.
     */
    private static void simulateDigitalRegisterButtonClick() {
        System.out.println("\n--- Digital Register Button Clicked ---");
        System.out.println("Accessing digital registers...");
    }

    /**
     * Prompts the user to enter an academic year and parses the input.
     *
     * @param scanner Scanner for user input.
     * @return An AcademicYear object if input is valid, null if user wants to exit or input is invalid.
     */
    private static AcademicYear promptForAcademicYear(Scanner scanner) {
        System.out.println("\nEnter the academic year (e.g., 2023-2024) or type 'exit' to quit:");
        System.out.print("Academic Year: ");
        String input = scanner.nextLine().trim();

        if (input.equalsIgnoreCase("exit")) {
            return null; // User wants to exit
        }

        try {
            String[] parts = input.split("-");
            if (parts.length == 2) {
                int startYear = Integer.parseInt(parts[0]);
                int endYear = Integer.parseInt(parts[1]);
                return new AcademicYear(startYear, endYear);
            } else {
                System.err.println("Invalid year format. Please use YYYY-YYYY (e.g., 2023-2024).");
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid year format. Please ensure years are numbers (e.g., 2023-2024).");
        } catch (IllegalArgumentException e) {
            System.err.println("Error creating academic year: " + e.getMessage());
        }
        // Re-prompt on invalid input. This creates a recursive call, which is fine for simple CLI.
        // For a more complex UI, a loop would be preferred.
        return promptForAcademicYear(scanner);
    }

    /**
     * Displays the list of digital registers, grouped by class.
     *
     * @param registers The list of DigitalRegister objects to display.
     */
    private static void displayRegisters(List<DigitalRegister> registers) {
        if (registers.isEmpty()) {
            System.out.println("No digital registers found for the selected academic year.");
            return;
        }

        System.out.println("\n--- Digital Registers Found ---");
        // Group registers by class for better readability
        registers.stream()
                .collect(Collectors.groupingBy(DigitalRegister::getClassInfo))
                .forEach((classInfo, classRegisters) -> {
                    System.out.println("\nClass: " + classInfo.getClassName());
                    classRegisters.forEach(register -> System.out.println("  - " + register.toString()));
                });
        System.out.println("------------------------------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SMOSServer smosServer = new SMOSServer();

        try {
            // Precondition 1: User must be logged in as direction
            if (!simulateLogin(scanner)) {
                System.out.println("Access denied. Exiting application.");
                return; // Exit if login fails
            }

            // Precondition 2: The user clicks on the "Digital Register" button
            simulateDigitalRegisterButtonClick();

            // Simulate a potential server disconnection for testing the error handling
            // smosServer.disconnect(); // Uncomment this line to test server interruption

            // Event sequence 1: System shows a screen for academic year selection
            // Event sequence 2: User selects the academic year
            AcademicYear selectedYear;
            while (true) { // Loop to allow multiple year selections or re-attempts
                if (!smosServer.isConnected()) {
                    System.err.println("SMOS server is disconnected. Attempting to reconnect...");
                    smosServer.connect(); // Try to reconnect
                    if (!smosServer.isConnected()) {
                        System.err.println("Failed to reconnect to SMOS server. Exiting operation.");
                        break; // Exit if reconnection fails
                    }
                }

                selectedYear = promptForAcademicYear(scanner);
                if (selectedYear == null) { // User chose to exit
                    System.out.println("Operation interrupted by user.");
                    break;
                }

                try {
                    // Event sequence 3: Search in the archive all digital registers of that year
                    // and displays them associated with the class to which they belong
                    List<DigitalRegister> foundRegisters = smosServer.getDigitalRegistersByAcademicYear(selectedYear);
                    displayRegisters(foundRegisters);

                    // Postcondition: The system is viewing the list of registers related to a particular academic year
                    System.out.println("\nSystem is viewing the list of registers for " + selectedYear.toString());

                } catch (SMOSServerException e) {
                    // Postcondition: The direction interrupts the operation connection to the SMOS server interrupted
                    System.err.println("Error: " + e.getMessage());
                    System.err.println("Please check server connection and try again.");
                    // The loop will continue, allowing the user to try again or exit.
                    // If the server is truly down, the `isConnected()` check at the start of the loop will handle it.
                } catch (Exception e) {
                    System.err.println("An unexpected error occurred: " + e.getMessage());
                    // For unexpected errors, we might want to break or offer more options.
                    // For now, we'll let the user try another year.
                }
            }
        } finally {
            // Ensure scanner is closed regardless of how the application exits
            System.out.println("Exiting ViewRegistersList application.");
            scanner.close();
            // Optionally, ensure server is disconnected cleanly
            if (smosServer.isConnected()) {
                smosServer.disconnect();
            }
        }
    }
}