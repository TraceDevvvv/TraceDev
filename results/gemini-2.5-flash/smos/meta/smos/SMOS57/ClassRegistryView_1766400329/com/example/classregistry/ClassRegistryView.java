package com.example.classregistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

/**
 * Main class for displaying class registry information.
 * This class simulates the user interface and interaction flow for a Professor
 * to view the register of a specific class.
 */
public class ClassRegistryView {

    private Professor loggedInProfessor;
    private SMOSServer smosServer;

    /**
     * Constructor for ClassRegistryView.
     * Initializes the view with a reference to the SMOS server.
     * @param smosServer The SMOS server instance to interact with.
     */
    public ClassRegistryView(SMOSServer smosServer) {
        this.smosServer = smosServer;
    }

    /**
     * Simulates the login process for a professor.
     * @param professor The professor attempting to log in.
     * @return true if login is successful, false otherwise.
     */
    public boolean loginProfessor(Professor professor) {
        // In a real system, this would involve authentication against a database.
        // For this simulation, we just set the loggedInProfessor.
        if (professor != null) {
            this.loggedInProfessor = professor;
            System.out.println("Professor " + professor.getName() + " logged in successfully.");
            return true;
        }
        System.out.println("Login failed: Invalid professor.");
        return false;
    }

    /**
     * Simulates displaying the list of classes taught by the logged-in professor.
     * This fulfills the precondition "VisualLancoclasses".
     * @return A list of ClassInfo objects taught by the professor.
     */
    public List<ClassInfo> displayProfessorClasses() {
        if (loggedInProfessor == null) {
            System.out.println("Error: No professor is logged in.");
            return new ArrayList<>();
        }
        System.out.println("\n--- Classes taught by Professor " + loggedInProfessor.getName() + " ---");
        List<ClassInfo> classes = loggedInProfessor.getTaughtClasses();
        if (classes.isEmpty()) {
            System.out.println("No classes found for this professor.");
        } else {
            for (int i = 0; i < classes.size(); i++) {
                System.out.println((i + 1) + ". " + classes.get(i).getClassName() + " (ID: " + classes.get(i).getClassId() + ")");
            }
        }
        return classes;
    }

    /**
     * Simulates the professor clicking the "Register" button for a specific class.
     * This method fetches and displays the class register information.
     *
     * Preconditions:
     * - The user is logged in as a teacher (handled by `loginProfessor`).
     * - The user has seen the list of classes (handled by `displayProfessorClasses`).
     * - The user clicks on the "Register" button associated with one of the classes.
     *
     * Events sequence:
     * 1. System displays information about the class register.
     *
     * Postconditions:
     * - The class registry information has been shown.
     * - Connection to the SMOS server is interrupted (closed).
     *
     * @param classId The ID of the class whose register is to be viewed.
     */
    public void viewClassRegister(String classId) {
        // Precondition check: Professor must be logged in
        if (loggedInProfessor == null) {
            System.out.println("Operation failed: No professor is logged in. Please log in first.");
            return;
        }

        // Precondition check: Ensure the professor teaches this class
        Optional<ClassInfo> selectedClass = loggedInProfessor.getTaughtClasses().stream()
                .filter(c -> c.getClassId().equals(classId))
                .findFirst();

        if (selectedClass.isEmpty()) {
            System.out.println("Error: Professor " + loggedInProfessor.getName() + " does not teach class with ID " + classId + ".");
            return;
        }

        System.out.println("\n--- Displaying Register for Class: " + selectedClass.get().getClassName() + " (ID: " + classId + ") ---");

        try {
            // Simulate connecting to SMOS server to fetch register data
            smosServer.connect();
            List<RegisterEntry> register = smosServer.getRegisterEntries(classId);

            if (register.isEmpty()) {
                System.out.println("No register entries found for this class.");
            } else {
                System.out.printf("%-12s %-10s %-20s %-10s %s%n", "Date", "Absences", "Disciplinary Notes", "Delays", "Justification");
                System.out.println("--------------------------------------------------------------------------------");
                for (RegisterEntry entry : register) {
                    System.out.printf("%-12s %-10d %-20s %-10d %s%n",
                            entry.getDate(),
                            entry.getAbsences(),
                            entry.getDisciplinaryNotes().isEmpty() ? "N/A" : entry.getDisciplinaryNotes(),
                            entry.getDelays(),
                            entry.getJustification().isEmpty() ? "N/A" : entry.getJustification());
                }
            }
            System.out.println("--- End of Register ---");

        } catch (SMOSServerException e) {
            System.err.println("Failed to retrieve class register: " + e.getMessage());
        } finally {
            // Postcondition: Connection to the SMOS server is interrupted (closed)
            smosServer.disconnect();
        }
    }

    /**
     * Main method to demonstrate the Class Registry View use case.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // 1. Setup: Create SMOS Server and Professor
        SMOSServer smosServer = new SMOSServer();
        Professor professor = new Professor("P001", "Dr. Alice Smith");

        // Create some classes and assign them to the professor
        ClassInfo mathClass = new ClassInfo("C101", "Mathematics 101");
        ClassInfo physicsClass = new ClassInfo("C102", "Physics 201");
        ClassInfo chemistryClass = new ClassInfo("C103", "Chemistry 101");

        professor.addTaughtClass(mathClass);
        professor.addTaughtClass(physicsClass);

        // Populate SMOS server with some dummy register data
        smosServer.addRegisterEntry("C101", new RegisterEntry(LocalDate.of(2023, 10, 26), 2, "Talking during lecture", 0, ""));
        smosServer.addRegisterEntry("C101", new RegisterEntry(LocalDate.of(2023, 10, 27), 0, "", 1, "Traffic delay"));
        smosServer.addRegisterEntry("C101", new RegisterEntry(LocalDate.of(2023, 10, 28), 1, "", 0, "Sick leave"));
        smosServer.addRegisterEntry("C102", new RegisterEntry(LocalDate.of(2023, 10, 26), 0, "", 0, ""));
        smosServer.addRegisterEntry("C102", new RegisterEntry(LocalDate.of(2023, 10, 29), 3, "Skipped class", 0, ""));


        // 2. Initialize the ClassRegistryView
        ClassRegistryView view = new ClassRegistryView(smosServer);

        // --- Simulate Use Case Flow ---

        // Precondition 1: The user is logged in to the system as a teacher
        System.out.println("--- Step 1: Professor Login ---");
        view.loginProfessor(professor);

        // Precondition 2: The user has held the case "VisualLancoclasses" and the system is
        // By displaying the list of all classes in which it teaches
        System.out.println("\n--- Step 2: Displaying Professor's Classes ---");
        List<ClassInfo> availableClasses = view.displayProfessorClasses();

        // Precondition 3: The user click on the "Register" button associated with one of the classes
        // Event Sequence 1: System displays information about the class register
        System.out.println("\n--- Step 3: Professor selects a class to view register ---");
        if (!availableClasses.isEmpty()) {
            // Simulate clicking "Register" for the first class (e.g., Mathematics 101)
            String selectedClassId = availableClasses.get(0).getClassId();
            System.out.println("Professor clicks 'Register' for class ID: " + selectedClassId);
            view.viewClassRegister(selectedClassId);
        } else {
            System.out.println("No classes available to view register for.");
        }

        // --- Demonstrate Edge Cases ---

        System.out.println("\n--- Demonstrating Edge Cases ---");

        // Edge Case 1: Attempt to view register without logging in
        System.out.println("\nAttempting to view register without logging in:");
        ClassRegistryView unloggedInView = new ClassRegistryView(smosServer);
        unloggedInView.viewClassRegister("C101");

        // Edge Case 2: Professor tries to view a class they don't teach
        System.out.println("\nProfessor tries to view a class they don't teach (e.g., Chemistry 101):");
        view.viewClassRegister("C103"); // Professor P001 does not teach C103

        // Edge Case 3: Viewing a class with no register entries (or non-existent class ID)
        System.out.println("\nViewing a class with no register entries (or non-existent class ID):");
        smosServer.addRegisterEntry("C104", new ArrayList<>()); // Add an empty register for C104
        professor.addTaughtClass(new ClassInfo("C104", "Empty Class")); // Professor teaches it
        view.viewClassRegister("C104");

        // Edge Case 4: SMOS server connection failure
        System.out.println("\nSimulating SMOS server connection failure:");
        SMOSServer faultySmosServer = new SMOSServer() {
            @Override
            public void connect() throws SMOSServerException {
                throw new SMOSServerException("Simulated connection error to SMOS server.");
            }
        };
        ClassRegistryView faultyView = new ClassRegistryView(faultySmosServer);
        faultyView.loginProfessor(professor);
        faultyView.viewClassRegister("C101");
    }
}