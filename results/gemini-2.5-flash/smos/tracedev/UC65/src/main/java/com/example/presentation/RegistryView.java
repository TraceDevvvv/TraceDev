
package com.example.presentation;

import com.example.application.RegistryService;
import com.example.dataaccess.IRegistryRepository;
import com.example.dataaccess.RegistryRepository;
import com.example.dataaccess.SMOSAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List; // Added import for List
import java.util.Locale;

/**
 * Represents the View layer of the application, responsible for displaying information to the user.
 * For this simulation, it uses System.out.println to mimic UI display.
 */
public class RegistryView {

    /**
     * Displays the detailed class registry information.
     *
     * @param registryData The ViewModel containing the data to display.
     */
    public void displayClassRegistry(RegistryViewModel registryData) {
        System.out.println("\n--- RegistryView: Displaying Class Registry (Steps 4-6) ---");
        if (registryData == null) {
            System.out.println("No registry data to display.");
            return;
        }

        System.out.println("Class Name: " + registryData.getClassName());
        System.out.println("Academic Year: " + registryData.getAcademicYear());
        System.out.println("----------------------------------------");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        if (registryData.getRegistryEntries().isEmpty()) {
            System.out.println("No entries found for this registry.");
        } else {
            for (RegistryEntryViewModel entry : registryData.getRegistryEntries()) {
                System.out.println("\nDate: " + dateFormat.format(entry.getEntryDate()));
                System.out.println("  Student Statuses:");
                if (entry.getStudentStatuses().isEmpty()) {
                    System.out.println("    No student statuses recorded for this date.");
                } else {
                    for (StudentStatusViewModel studentStatus : entry.getStudentStatuses()) {
                        String status = studentStatus.isPresent() ? "Present" : "Absent";
                        if (studentStatus.isDelayed()) {
                            status += " (Delayed)";
                        }
                        System.out.println("    - " + studentStatus.getStudentName() + ": " + status);
                        System.out.println("      Justification: " + studentStatus.getJustificationDescription());
                        System.out.println("      Disciplinary Note: " + studentStatus.getDisciplinaryNoteText());
                    }
                }
            }
        }
        System.out.println("--- End Class Registry Display ---");
    }

    /**
     * Displays a form for managing justifications.
     * (Simulated by printing a message).
     */
    public void displayJustificationForm() {
        System.out.println("\n--- RegistryView: Displaying Justification Form (Step 7) ---");
        System.out.println("  (Simulated: Form for adding/editing student justifications)");
        System.out.println("--- End Justification Form Display ---");
    }

    /**
     * Displays a form for managing disciplinary notes.
     * (Simulated by printing a message).
     */
    public void displayDisciplinaryNoteForm() {
        System.out.println("\n--- RegistryView: Displaying Disciplinary Note Form (Step 8) ---");
        System.out.println("  (Simulated: Form for adding/editing student disciplinary notes)");
        System.out.println("--- End Disciplinary Note Form Display ---");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("\n!!! RegistryView: ERROR Message Display !!!");
        System.err.println("  " + message);
        System.err.println("!!! End Error Message !!!");
    }

    /**
     * Simulates the Direction reviewing the displayed information.
     */
    public void reviewsDisplayedInformation() {
        System.out.println("\n--- Direction: Reviews Displayed Information (Step 9) ---");
        System.out.println("  (Simulated: User reviews the class registry details)");
        System.out.println("--- End Review ---");
    }

    /**
     * Main method to demonstrate the application flow, acting as the entry point
     * and simulating user interaction (the 'Actor Direction').
     */
    public static void main(String[] args) {
        System.out.println("--- Application Start: Simulating 'View Class Registry Details' Use Case ---");

        // Bootstrap the application (Dependency Injection)
        SMOSAdapter smosAdapter = new SMOSAdapter();
        IRegistryRepository registryRepository = new RegistryRepository(smosAdapter);
        RegistryService registryService = new RegistryService(registryRepository);
        RegistryView registryView = new RegistryView();
        AuthenticationService authenticationService = new AuthenticationService();
        RegistrySelectionService registrySelectionService = new RegistrySelectionService();
        AcademicYearRegistryList academicYearRegistryList = new AcademicYearRegistryList();

        RegistryController registryController = new RegistryController(
                registryService, registryView, authenticationService,
                registrySelectionService, academicYearRegistryList
        );

        // Preconditions check (R3, R4, R5)
        System.out.println("\n--- Preconditions Check ---");
        boolean isLoggedIn = authenticationService.checkLoggedInStatus();
        String selectedClassIdContext = registrySelectionService.getSelectedRegistryContext();
        List<String> availableClassIds = academicYearRegistryList.getAvailableClassIds();

        // Simulate user clicking 'Register' button for a specific class ID.
        // Assuming "class101" is selected, as per the RegistrySelectionService context.
        String classIdToView = "class101";

        if (isLoggedIn && selectedClassIdContext.equals(classIdToView) && availableClassIds.contains(classIdToView)) {
            System.out.println("\n[Direction] User clicks 'Register' button for Class ID: " + classIdToView);
            // Initiate the use case flow
            registryController.viewClassRegistry(classIdToView);
        } else {
            System.err.println("\nPreconditions not met to view class registry:");
            if (!isLoggedIn) System.err.println("- User not logged in (R3)");
            if (!selectedClassIdContext.equals(classIdToView)) System.err.println("- Selected registry context does not match (R4)");
            if (!availableClassIds.contains(classIdToView)) System.err.println("- Class ID not in available list (R5)");
        }

        System.out.println("\n--- Application End ---");
    }
}
