package com.example.absencesystem;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Main class to demonstrate the functionality of the Absence Registry System.
 * This class simulates the administrator's interaction with the system,
 * following the use case "EnterAbsencesAdmin".
 */
public class Main {

    public static void main(String[] args) {
        // Initialize the Absence Registry System
        AbsenceRegistrySystem system = new AbsenceRegistrySystem();
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Absence Registry System Demonstration ---");

        // Precondition: The user must be logged in to the system as an administrator.
        // Simulate administrator login
        System.out.println("\nAttempting Administrator Login...");
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            loggedIn = system.login(username, password);
            if (!loggedIn) {
                System.out.println("Invalid credentials. Please try again.");
            }
        }

        // Precondition: The user selects the date on which he wants to enter the information.
        // Event Sequence Step 1: Update the screen displayed according to the date
        System.out.println("\n--- Step 1: Administrator selects a date ---");
        LocalDate entryDate = LocalDate.of(2023, 10, 26); // Example date
        try {
            system.selectDate(entryDate);
            System.out.println("Screen updated to display data entry form for: " + system.getSelectedDate());
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.err.println("Error selecting date: " + e.getMessage());
            scanner.close();
            return; // Exit if date selection fails
        }

        // Event Sequence Step 2: Fill out the form by entering absent students / present and click "Save"
        System.out.println("\n--- Step 2: Administrator fills out the form ---");
        System.out.println("Retrieving all students for the form...");
        List<Student> allStudents = system.getAllStudents();
        System.out.println("Total students: " + allStudents.size());

        // Simulate filling out the form:
        // For demonstration, we'll pre-define some absence entries.
        // In a real UI, the admin would select students and their absence status.
        Map<String, AbsenceRegistrySystem.AbsenceEntry> studentAbsenceData = new HashMap<>();

        // Student S001 (Alice Smith) is absent with a reason
        studentAbsenceData.put("S001", new AbsenceRegistrySystem.AbsenceEntry(AbsenceType.ABSENCE, "Fever"));
        // Student S002 (Bob Johnson) is delayed without a specific reason
        studentAbsenceData.put("S002", new AbsenceRegistrySystem.AbsenceEntry(AbsenceType.DELAY, null));
        // Student S003 (Charlie Brown) is present
        studentAbsenceData.put("S003", new AbsenceRegistrySystem.AbsenceEntry(AbsenceType.PRESENT, null));
        // Student S004 (Diana Prince) is absent without a specific reason
        studentAbsenceData.put("S004", new AbsenceRegistrySystem.AbsenceEntry(AbsenceType.ABSENCE, null));
        // Simulate an entry for a non-existent student (should be handled gracefully)
        studentAbsenceData.put("S999", new AbsenceRegistrySystem.AbsenceEntry(AbsenceType.ABSENCE, "Unknown student"));


        System.out.println("Simulating 'Save' button click...");
        List<Absence> newlyRecordedAbsences = null;
        try {
            newlyRecordedAbsences = system.enterAbsenceData(studentAbsenceData);
            System.out.println("Absence data saved successfully.");
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.err.println("Error entering absence data: " + e.getMessage());
            scanner.close();
            return; // Exit if data entry fails
        }

        // Postcondition: The data of absences and delays have been entered in the system,
        // and the system sent notifications to parents.
        // Event Sequence Step 3: Send data to the server. The server for each absence sends a notification e-mail to the parent of the student.
        // (This is handled internally by `enterAbsenceData` calling `NotificationService`)
        System.out.println("\n--- Step 3: Notifications sent (simulated in console output above) ---");
        System.out.println("Notifications for newly recorded absences have been sent to parents.");

        // Event Sequence Step 4: Displays the updated log data.
        System.out.println("\n--- Step 4: Displaying updated log data for " + entryDate + " ---");
        try {
            List<Absence> recordsForDate = system.getAbsenceRecordsForDate(entryDate);
            if (recordsForDate.isEmpty()) {
                System.out.println("No absence records found for " + entryDate + ".");
            } else {
                System.out.println("Absence records for " + entryDate + ":");
                for (Absence absence : recordsForDate) {
                    System.out.println("- " + absence);
                }
            }
        } catch (IllegalStateException | IllegalArgumentException e) {
            System.err.println("Error retrieving absence records: " + e.getMessage());
        }

        // Postcondition: The system remains on the registry screen.
        System.out.println("\n--- System remains on the registry screen ---");
        System.out.println("Administrator can continue to select another date or enter more data.");

        // Simulate administrator interrupting the operation (e.g., logging out)
        System.out.println("\n--- Administrator interrupts the operation (logging out) ---");
        system.logout();

        // Demonstrate attempting actions after logout (should fail)
        System.out.println("\nAttempting to select date after logout (should fail):");
        try {
            system.selectDate(LocalDate.now());
        } catch (IllegalStateException e) {
            System.out.println("Expected error: " + e.getMessage());
        }

        scanner.close();
        System.out.println("\n--- Demonstration Complete ---");
    }
}