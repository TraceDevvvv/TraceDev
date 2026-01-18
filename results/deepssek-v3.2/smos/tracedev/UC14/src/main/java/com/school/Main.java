package com.school;

/**
 * Main class to demonstrate the sequence of operations.
 */
public class Main {
    public static void main(String[] args) {
        // Setup
        Administrator admin = new Administrator("admin1", "John Doe");
        SessionManager sessionManager = new SessionManager(admin);
        ClassValidator validator = new ClassValidator();
        ClassArchive archive = new ClassArchive();
        ClassRepository repo = new ClassRepositoryImpl(archive);
        InsertClassController controller = new InsertClassController(validator, repo);
        ClassFormUI formUI = new ClassFormUI(controller);
        ClassListUI listUI = new ClassListUI(sessionManager, 2024);
        listUI.setClassFormUI(formUI);

        System.out.println("=== Scenario 1: Normal successful insertion ===");
        // Precondition: viewing class list
        listUI.displayClasses(2024);
        // User clicks "New Class"
        listUI.navigateToNewClassForm();
        // Display form
        formUI.displayForm();
        // Submit valid data
        ClassFormDTO dto = new ClassFormDTO("Math 101", "Room 201", 2024);
        formUI.submitForm(dto);

        System.out.println("\n=== Scenario 2: Validation error ===");
        ClassFormDTO invalidDto = new ClassFormDTO("", "Room 202", 2024);
        formUI.submitForm(invalidDto);

        System.out.println("\n=== Scenario 3: Network error ===");
        archive.setConnectionAvailable(false);
        ClassFormDTO dto2 = new ClassFormDTO("Science 101", "Room 301", 2024);
        formUI.submitForm(dto2);
        archive.setConnectionAvailable(true); // restore

        System.out.println("\n=== Scenario 4: User cancellation ===");
        formUI.simulateCancel();
    }
}