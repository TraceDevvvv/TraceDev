import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Comparator;

/**
 * Main application entry point for the 'View Registers List' use case.
 * This class simulates the interaction of an administrator logging in,
 * selecting an academic year, and viewing the digital registers associated
 * with that year and their respective classes.
 */
public class ViewRegistersListApp {

    private Administrator currentAdmin;
    private RegisterArchive registerArchive;
    private Scanner scanner;

    /**
     * Constructs the ViewRegistersListApp.
     * Initializes the scanner, register archive, and populates it with sample data.
     */
    public ViewRegistersListApp() {
        this.scanner = new Scanner(System.in);
        this.registerArchive = new RegisterArchive();
        // Initialize with a dummy administrator for demonstration
        this.currentAdmin = new Administrator("admin", "admin123");
        populateSampleData();
    }

    /**
     * Populates the register archive with some sample digital registers.
     * This simulates existing data in the system.
     */
    private void populateSampleData() {
        // Define some academic years
        AcademicYear year2022_2023 = new AcademicYear(2022, 2023);
        AcademicYear year2023_2024 = new AcademicYear(2023, 2024);
        AcademicYear year2024_2025 = new AcademicYear(2024, 2025);

        // Define some classes
        ClassInfo classA = new ClassInfo("10A", "Tenth Grade - Section A");
        ClassInfo classB = new ClassInfo("10B", "Tenth Grade - Section B");
        ClassInfo classC = new ClassInfo("11C", "Eleventh Grade - Section C");
        ClassInfo classD = new ClassInfo("12D", "Twelfth Grade - Section D");

        // Create and add digital registers
        registerArchive.addRegister(new DigitalRegister("REG001", "Attendance Q1", year2022_2023, classA));
        registerArchive.addRegister(new DigitalRegister("REG002", "Grades Midterm", year2022_2023, classA));
        registerArchive.addRegister(new DigitalRegister("REG003", "Attendance Q2", year2022_2023, classB));
        registerArchive.addRegister(new DigitalRegister("REG004", "Grades Final", year2022_2023, classB));

        registerArchive.addRegister(new DigitalRegister("REG005", "Attendance Q1", year2023_2024, classA));
        registerArchive.addRegister(new DigitalRegister("REG006", "Grades Midterm", year2023_2024, classC));
        registerArchive.addRegister(new DigitalRegister("REG007", "Attendance Q2", year2023_2024, classD));
        registerArchive.addRegister(new DigitalRegister("REG008", "Grades Final", year2023_2024, classA));

        registerArchive.addRegister(new DigitalRegister("REG009", "Attendance Q1", year2024_2025, classC));
        // No registers for year2024_2025 for classD to demonstrate empty case
    }

    /**
     * Starts the application flow, including login and main menu.
     */
    public void start() {
        System.out.println("Welcome to the Digital Register System.");
        if (adminLogin()) {
            System.out.println("\nAdministrator logged in successfully. Clicked on 'Digital Register' button.");
            displayAcademicYearSelectionScreen();
        } else {
            System.out.println("Application terminated due to login failure.");
        }
        scanner.close(); // Close the scanner when the application finishes
    }

    /**
     * Handles the administrator login process.
     *
     * @return true if login is successful, false otherwise.
     */
    private boolean adminLogin() {
        System.out.println("\n--- Administrator Login ---");
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (currentAdmin.login(username, password)) {
                return true;
            } else {
                attempts++;
                System.out.println("Invalid credentials. " + (MAX_ATTEMPTS - attempts) + " attempts remaining.");
            }
        }
        System.out.println("Maximum login attempts reached. Exiting.");
        return false;
    }

    /**
     * Displays the screen for academic year selection and processes the user's choice.
     */
    private void displayAcademicYearSelectionScreen() {
        List<AcademicYear> availableYears = registerArchive.getAllAcademicYears();
        if (availableYears.isEmpty()) {
            System.out.println("\nNo academic years with registers found in the archive.");
            return;
        }

        // Sort years for consistent display
        availableYears.sort(Comparator.comparingInt(AcademicYear::getStartYear));

        System.out.println("\n--- Select Academic Year ---");
        System.out.println("Available Academic Years:");
        for (int i = 0; i < availableYears.size(); i++) {
            System.out.println((i + 1) + ". " + availableYears.get(i));
        }
        System.out.println("0. Exit");

        AcademicYear selectedYear = null;
        while (selectedYear == null) {
            System.out.print("Enter your choice (number): ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0) {
                    System.out.println("Exiting academic year selection.");
                    return; // Exit the method
                }
                if (choice > 0 && choice <= availableYears.size()) {
                    selectedYear = availableYears.get(choice - 1);
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 and " + availableYears.size() + " or 0 to exit.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        // If a year is selected, search and display registers
        if (selectedYear != null) {
            searchAndDisplayRegisters(selectedYear);
        }
    }

    /**
     * Searches the archive for digital records of the selected academic year
     * and displays them associated with their class.
     *
     * @param academicYear The academic year for which to display registers.
     */
    private void searchAndDisplayRegisters(AcademicYear academicYear) {
        System.out.println("\n--- Digital Registers for Academic Year: " + academicYear + " ---");

        List<DigitalRegister> registers = registerArchive.getRegistersByAcademicYear(academicYear);

        if (registers.isEmpty()) {
            System.out.println("No digital registers found for academic year " + academicYear + ".");
        } else {
            // Group registers by class for better readability
            registers.sort(Comparator.comparing(r -> r.getClassInfo().getClassName()));

            String currentClassId = "";
            for (DigitalRegister register : registers) {
                if (!register.getClassInfo().getClassId().equals(currentClassId)) {
                    currentClassId = register.getClassInfo().getClassId();
                    System.out.println("\n  Class: " + register.getClassInfo().getClassName() + " (" + register.getClassInfo().getClassId() + ")");
                    System.out.println("  --------------------------------------------------");
                }
                System.out.println("    - " + register.getRegisterName() + " (ID: " + register.getRegisterId() + ")");
            }
        }
        System.out.println("\n--- End of List ---");
        // Postcondition: The system is displaying the list of registers related to a particular academic year.
        // The administrator can now choose to interrupt the connection (simulated by program end or further menu options).
    }

    /**
     * Main method to run the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        ViewRegistersListApp app = new ViewRegistersListApp();
        app.start();
    }
}