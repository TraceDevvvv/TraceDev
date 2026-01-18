package com.example.school;

/**
 * Main class to demonstrate the application flow as per the sequence diagrams.
 * This class sets up the dependencies and simulates user interactions.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Starting School Registry Application Demonstration.");

        // 1. Initialize Repositories (Mock Implementations)
        IClassRepository classRepository = new ClassRepository();
        IAbsenceRepository absenceRepository = new AbsenceRepository();
        IDisciplinaryNoteRepository disciplinaryNoteRepository = new DisciplinaryNoteRepository();
        IDelayRepository delayRepository = new DelayRepository();

        // 2. Initialize Application Serv
        ClassRegistryApplicationService classRegistryService = new ClassRegistryApplicationService(
                classRepository, absenceRepository, disciplinaryNoteRepository, delayRepository);
        ClassListApplicationService classListService = new ClassListApplicationService(classRepository); // EC3

        // 3. Initialize Controllers
        ClassRegistryController classRegistryController = new ClassRegistryController(classRegistryService);
        ClassListController classListController = new ClassListController(classListService); // EC3

        // 4. Initialize ProfessorView with controllers
        ProfessorView professorView = new ProfessorView(classRegistryController, classListController);

        String professorId = "P001"; // Professor P001 teaches C101, C102, C104
        String classIdToView = "C101"; // Example class to view registry for

        // --- Simulate Sequence Diagram Flow ---

        // Scenario 1: Successful initialization and class list display (EC3)
        System.out.println("\n--- Scenario 1: Successful Class List Loading ---");
        professorView.initializeView(professorId);

        // Scenario 2: Successful viewing of a class registry
        System.out.println("\n--- Scenario 2: Successful Class Registry Viewing for " + classIdToView + " ---");
        professorView.onRegisterButtonClick(classIdToView);

        // Scenario 3: Simulate SMOS connection error during class list loading (ExC2)
        System.out.println("\n--- Scenario 3: SMOS Connection Error during Class List Loading ---");
        // To force an error, we can temporarily change the EXCEPTION_PROBABILITY in repositories
        // or manually make a repository throw an exception for demonstration.
        // For this example, let's just make the view call again, hoping for the random error.
        System.out.println("Attempting to initialize view (hoping for a random connection error)...");
        professorView.initializeView(professorId); // This might fail due to random EXCEPTION_PROBABILITY

        // Scenario 4: Simulate SMOS connection error during class registry loading (ExC2)
        System.out.println("\n--- Scenario 4: SMOS Connection Error during Class Registry Loading ---");
        System.out.println("Attempting to view class registry (hoping for a random connection error)...");
        professorView.onRegisterButtonClick(classIdToView); // This might fail due to random EXCEPTION_PROBABILITY

        System.out.println("\nDemonstration Finished.");
    }
}