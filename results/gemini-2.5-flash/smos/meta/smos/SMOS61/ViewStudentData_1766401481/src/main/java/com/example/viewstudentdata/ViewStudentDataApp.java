package com.example.viewstudentdata;

import java.util.List;
import java.time.format.DateTimeFormatter;

/**
 * Main application class for the ViewStudentData use case.
 * This class orchestrates the flow for a parent to view their child's academic and behavioral data.
 * It simulates user login, child selection, data retrieval, and interaction with an external system.
 */
public class ViewStudentDataApp {

    /**
     * The main method, entry point of the application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        System.out.println("--- View Student Data Application Started ---");

        // 1. Initialize StudentService and mock data
        StudentService studentService = new StudentService();
        studentService.initMockData();
        System.out.println("\nStudentService initialized with mock data.");

        // 2. Simulate Parent Login and 'Register' button click (selecting a child)
        // Preconditions:
        // - The user is logged in to the system as a parent
        // - The user clicks the "Register" button associated with one of his children
        String loggedInParentId = "parent123"; // Simulating a logged-in parent
        String selectedChildId = "student101"; // Simulating the parent selecting a child

        System.out.println("\nSimulating parent '" + loggedInParentId + "' attempting to view data for child '" + selectedChildId + "'.");

        // 3. Check if the selected child belongs to the logged-in parent (authorization check)
        if (studentService.isChildOfParent(loggedInParentId, selectedChildId)) {
            System.out.println("Authorization successful: Child '" + selectedChildId + "' belongs to parent '" + loggedInParentId + "'.");

            // 4. Retrieve student records for the authorized child
            List<StudentRecord> records = studentService.getStudentRecordsForParentAndChild(loggedInParentId, selectedChildId);

            // 5. Display the summary table
            displayStudentSummary(records, selectedChildId);

            // 6. Simulate interruption of SMOS server connection (Postcondition)
            SMOSIntegrationService smosService = new SMOSIntegrationService();
            smosService.interruptSMOSConnection(loggedInParentId);
        } else {
            // 7. Handle unauthorized access
            displayErrorMessage("Access denied: Child '" + selectedChildId + "' is not associated with parent '" + loggedInParentId + "'.");
        }

        System.out.println("\n--- View Student Data Application Ended ---");

        // Simulate another scenario: parent trying to view a child that doesn't belong to them
        System.out.println("\n--- Simulating another scenario: Unauthorized access attempt ---");
        String unauthorizedParentId = "parent123";
        String unauthorizedChildId = "student103"; // This child belongs to parent456

        System.out.println("\nSimulating parent '" + unauthorizedParentId + "' attempting to view data for child '" + unauthorizedChildId + "'.");
        if (studentService.isChildOfParent(unauthorizedParentId, unauthorizedChildId)) {
            List<StudentRecord> records = studentService.getStudentRecordsForParentAndChild(unauthorizedParentId, unauthorizedChildId);
            displayStudentSummary(records, unauthorizedChildId);
            SMOSIntegrationService smosService = new SMOSIntegrationService();
            smosService.interruptSMOSConnection(unauthorizedParentId);
        } else {
            displayErrorMessage("Access denied: Child '" + unauthorizedChildId + "' is not associated with parent '" + unauthorizedParentId + "'.");
        }
        System.out.println("\n--- End of Unauthorized Access Attempt Scenario ---");
    }

    /**
     * Displays a summary table of student records to the console.
     *
     * @param records   A list of StudentRecord objects to display.
     * @param studentId The ID of the student whose records are being displayed.
     */
    private static void displayStudentSummary(List<StudentRecord> records, String studentId) {
        if (records.isEmpty()) {
            System.out.println("\nNo records found for student ID: " + studentId);
            return;
        }

        System.out.println("\n--- Student Data Summary for Student ID: " + studentId + " ---");
        System.out.printf("%-12s | %-9s | %-15s | %-30s | %-7s | %s%n",
                "Date", "Absences", "Delays", "Disciplinary Notes", "Justification", "Record ID");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (StudentRecord record : records) {
            System.out.printf("%-12s | %-9d | %-15d | %-30.30s | %-7.7s | %s%n",
                    record.getDate().format(dateFormatter),
                    record.getAbsences(),
                    record.getDelays(),
                    record.getDisciplinaryNotes().isEmpty() ? "N/A" : record.getDisciplinaryNotes(),
                    record.getJustification().isEmpty() ? "N/A" : record.getJustification(),
                    record.getRecordId());
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("Postcondition: Information relating to one of the user's children displayed.");
    }

    /**
     * Displays an error message to the console.
     *
     * @param message The error message to display.
     */
    private static void displayErrorMessage(String message) {
        System.err.println("\nERROR: " + message);
    }
}