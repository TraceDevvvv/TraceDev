package com.absenceapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Main application class to demonstrate the Absence Management System.
 * Initializes all components and simulates user interaction.
 */
public class MainApp {

    public static void main(String[] args) throws ParseException {
        // 1. Initialize Infrastructure Components
        // REQ-005: Initialize SMO Server Client
        ISMOServerClient smoServerClient = new SMOServerClientImpl();
        IAbsenceRepository absenceRepository = new AbsenceRepositoryImpl(smoServerClient); // Injects SMO client
        INotificationService notificationService = new EmailNotificationService();
        // REQ-001: Initialize Registration Service (dummy)
        SRegistrationService registrationService = new SRegistrationService() {
            @Override
            public boolean isRegistrationCompleted(String userId) {
                System.out.println("SRegistrationService: Checking registration for user: " + userId + " - assuming true.");
                return true; // Always return true for simulation
            }
        };

        // 2. Initialize Application Service
        AbsenceService absenceService = new AbsenceService(absenceRepository, notificationService, registrationService);

        // 3. Initialize UI Components
        AbsenceView absenceView = new AbsenceView();
        AbsenceViewController absenceViewController = new AbsenceViewController(absenceService, absenceView);

        // Simulate User Interaction (Administrator)
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Scanner scanner = new Scanner(System.in);
        Date today = dateFormatter.parse(dateFormatter.format(new Date())); // Normalize today's date

        System.out.println("--- Absence Management System Simulation ---");

        // REQ-006 & REQ-007: Administrator selects a date
        System.out.println("\n--- Step 1: Administrator selects a date to view absences ---");
        absenceView.onDateSelected(today); // Display absences for today

        // Simulate a slight delay for user to read
        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // REQ-008: Administrator makes changes (add, update, delete)
        System.out.println("\n--- Step 2: Administrator edits absences ---");
        // Add a new absence
        Date futureDate = dateFormatter.parse("2024-07-20");
        absenceView.editAbsenceDetails("add", "s103", futureDate, AbsenceType.ABSENT.name(), "Sick leave", null);
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // Update an existing absence (abs1 for s101, which is today)
        // Note: The specific ID "abs1" is assumed from AbsenceRepositoryImpl's initial data.
        // In a real UI, the admin would select it from the displayed list.
        String abs1Id = "abs1"; // ID of an initial absence record
        absenceView.editAbsenceDetails("update", "s101", today, AbsenceType.EXCUSED.name(), "Doctor appointment (modified)", null);
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // Mark an absence for deletion (abs2 for s102, which is today)
        String abs2Id = "abs2"; // ID of another initial absence record
        absenceView.editAbsenceDetails("delete", null, null, null, null, abs2Id);
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // REQ-010: Administrator clicks SAVE
        System.out.println("\n--- Step 3: Administrator clicks SAVE ---");
        absenceView.onSaveClicked();

        // Simulate delay to observe effects
        try { Thread.sleep(5000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // Verify changes by re-selecting the date (or future date)
        System.out.println("\n--- Step 4: Administrator re-selects today's date to verify changes ---");
        absenceView.onDateSelected(today);
        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        System.out.println("\n--- Step 5: Administrator selects future date to verify new absence ---");
        absenceView.onDateSelected(futureDate);
        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // Simulate an error scenario (e.g., SMO server connection failure)
        System.out.println("\n--- Step 6: Simulate an error scenario (SMO Server connection failure) ---");
        System.out.println("--- Administrator attempts to add a new absence ---");
        // Force SMO client to throw an exception
        ((SMOServerClientImpl)smoServerClient).setShouldThrowException(true);

        absenceView.onDateSelected(today); // Re-fetch current data
        absenceView.editAbsenceDetails("add", "s101", today, AbsenceType.TARDY.name(), "Late bus (error test)", null);
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        absenceView.onSaveClicked(); // This should trigger the error path
        try { Thread.sleep(5000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        // REQ-009: Administrator clicks CANCEL
        System.out.println("\n--- Step 7: Administrator clicks CANCEL (after error or before save) ---");
        ((SMOServerClientImpl)smoServerClient).setShouldThrowException(false); // Reset SMO client
        absenceView.onDateSelected(today); // Get fresh data
        // Simulate some more changes that will be cancelled
        absenceView.editAbsenceDetails("add", "s102", today, AbsenceType.ABSENT.name(), "Cancelled absence", null);
        absenceView.editAbsenceDetails("update", "s101", today, AbsenceType.PRESENT.name(), "Changed to present (cancelled)", null);
        try { Thread.sleep(1000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        absenceView.onCancelClicked(); // This should revert the display to the last saved state

        System.out.println("\n--- Simulation Complete ---");
        scanner.close();
    }
}