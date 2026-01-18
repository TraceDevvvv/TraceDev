package com.example.attendancesystem;

/**
 * Main class to simulate the sequence diagram for "Register Digitization and Collect Absence Data".
 * This class sets up the application components and orchestrates the user interactions.
 */
public class Main {
    public static void main(String[] args) {
        // --- Setup: Instantiate all components and wire them together ---
        System.out.println("--- System Initialization ---");

        // Infrastructure Layer Components
        InMemoryStudentRepository studentRepository = new InMemoryStudentRepository();
        InMemoryAttendanceRepository attendanceRepository = new InMemoryAttendanceRepository();
        EmailService emailService = new EmailService();
        ExternalServerGateway externalServerGateway = new ExternalServerGateway();

        // Application (Service) Layer Component
        AttendanceService attendanceService = new AttendanceService(
                studentRepository, attendanceRepository, emailService, externalServerGateway);

        // Presentation Layer Components
        // To handle circular dependency (View needs Controller, Controller needs View for direct calls),
        // we instantiate Controller first, then View, then inject View back into Controller.
        AttendanceController attendanceController = new AttendanceController(attendanceService);
        AttendanceView attendanceView = new AttendanceView(attendanceController);
        attendanceController.setView(attendanceView); // Inject view into controller

        System.out.println("--- Initialization Complete ---\n");

        // --- Scenario 1: Successful Attendance Recording ---
        System.out.println("\n========== Scenario 1: Successful Attendance Recording ==========");
        // ATAStaff -> Controller : requestAttendanceScreen (via View acting as proxy for initial user action)
        attendanceView.requestAttendanceScreen("CLASS_A");
        // View displays students, ATAStaff makes selections (simulated by getAttendanceData in View)
        // ATAStaff -> View : clickSaveButton()
        attendanceView.clickSaveButton();

        System.out.println("\nTotal attendance records saved in repository after Scenario 1:");
        attendanceRepository.findAll().forEach(System.out::println);


        // --- Scenario 2: User cancels operation ---
        System.out.println("\n\n========== Scenario 2: User Cancels Operation ==========");
        attendanceView.requestAttendanceScreen("CLASS_B"); // Load another class's attendance
        // ATAStaff -> View : clickCancel()
        attendanceView.clickCancel();

        System.out.println("\nTotal attendance records saved in repository after Scenario 2 (should be same as after Scen. 1):");
        attendanceRepository.findAll().forEach(System.out::println);


        // --- Scenario 3: External Server Error Simulation ---
        System.out.println("\n\n========== Scenario 3: External Server Error ==========");
        externalServerGateway.setSimulateError(true); // Configure gateway to throw an error

        attendanceView.requestAttendanceScreen("CLASS_A"); // Load attendance again
        attendanceView.clickSaveButton(); // This call will trigger the error path

        System.out.println("\nTotal attendance records saved in repository after Scenario 3:");
        // IMPORTANT NOTE: The current sequence diagram and class diagram imply that the internal
        // AttendanceRepository.save() happens *before* the call to ExternalServerGateway.sendAbsenceData().
        // Therefore, even if the ExternalServerGateway fails, the records for Scenario 3
        // are still saved internally. A more robust system might use transaction management
        // to rollback the internal save if the external call fails, or implement a retry mechanism.
        attendanceRepository.findAll().forEach(System.out::println);

        externalServerGateway.setSimulateError(false); // Reset for potential future scenarios

        System.out.println("\n\n========== Simulation End ==========");
    }
}