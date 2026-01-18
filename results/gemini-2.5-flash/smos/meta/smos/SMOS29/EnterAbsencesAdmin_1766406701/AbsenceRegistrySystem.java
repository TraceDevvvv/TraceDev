package com.example.absencesystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Main application logic for the Absence Registry System.
 * This class handles administrator login, student and absence data management,
 * and integrates with the NotificationService to send parent notifications.
 * It simulates a backend system that processes absence entries.
 */
public class AbsenceRegistrySystem {

    // Simulated in-memory databases
    private final Map<String, Student> students; // studentId -> Student object
    private final Map<String, Administrator> administrators; // username -> Administrator object
    private final List<Absence> absenceRecords; // List of all recorded absences

    private final NotificationService notificationService;
    private Administrator loggedInAdministrator;
    private LocalDate selectedDate; // The date currently selected by the administrator

    /**
     * Constructs a new AbsenceRegistrySystem.
     * Initializes simulated student and administrator data, and the notification service.
     */
    public AbsenceRegistrySystem() {
        this.students = new HashMap<>();
        this.administrators = new HashMap<>();
        this.absenceRecords = new ArrayList<>();
        this.notificationService = new NotificationService();
        this.loggedInAdministrator = null;
        this.selectedDate = null;

        // Initialize with some dummy data for demonstration
        initializeDummyData();
    }

    /**
     * Initializes dummy student and administrator data for testing purposes.
     */
    private void initializeDummyData() {
        // Add dummy students
        Student s1 = new Student("S001", "Alice Smith", "alice.parent@example.com");
        Student s2 = new Student("S002", "Bob Johnson", "bob.parent@example.com");
        Student s3 = new Student("S003", "Charlie Brown", "charlie.parent@example.com");
        Student s4 = new Student("S004", "Diana Prince", "diana.parent@example.com");

        students.put(s1.getStudentId(), s1);
        students.put(s2.getStudentId(), s2);
        students.put(s3.getStudentId(), s3);
        students.put(s4.getStudentId(), s4);

        // Add dummy administrators
        Administrator admin1 = new Administrator("admin", "admin123");
        Administrator admin2 = new Administrator("mike", "securepass");

        administrators.put(admin1.getUsername(), admin1);
        administrators.put(admin2.getUsername(), admin2);
    }

    /**
     * Attempts to log in an administrator.
     *
     * @param username The username provided by the user.
     * @param password The password provided by the user.
     * @return true if login is successful, false otherwise.
     */
    public boolean login(String username, String password) {
        Administrator admin = administrators.get(username);
        if (admin != null && admin.authenticate(username, password)) {
            this.loggedInAdministrator = admin;
            System.out.println("Administrator '" + username + "' logged in successfully.");
            return true;
        }
        this.loggedInAdministrator = null; // Ensure no admin is logged in on failure
        System.out.println("Login failed for username: " + username);
        return false;
    }

    /**
     * Logs out the current administrator.
     */
    public void logout() {
        if (loggedInAdministrator != null) {
            System.out.println("Administrator '" + loggedInAdministrator.getUsername() + "' logged out.");
            this.loggedInAdministrator = null;
            this.selectedDate = null; // Clear selected date upon logout
        }
    }

    /**
     * Checks if an administrator is currently logged in.
     *
     * @return true if an administrator is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return loggedInAdministrator != null;
    }

    /**
     * Returns the currently logged-in administrator.
     *
     * @return The Administrator object if logged in, null otherwise.
     */
    public Administrator getLoggedInAdministrator() {
        return loggedInAdministrator;
    }

    /**
     * Sets the date for which absence information will be entered.
     * This simulates the "selects the date on which he wants to enter the information" step.
     *
     * @param date The date selected by the administrator.
     * @throws IllegalStateException if no administrator is logged in.
     * @throws IllegalArgumentException if the date is null.
     */
    public void selectDate(LocalDate date) {
        if (!isLoggedIn()) {
            throw new IllegalStateException("Administrator must be logged in to select a date.");
        }
        if (date == null) {
            throw new IllegalArgumentException("Selected date cannot be null.");
        }
        this.selectedDate = date;
        System.out.println("Date selected for absence entry: " + date);
    }

    /**
     * Returns the currently selected date for absence entry.
     *
     * @return The selected LocalDate, or null if no date has been selected.
     */
    public LocalDate getSelectedDate() {
        return selectedDate;
    }

    /**
     * Retrieves a list of all registered students.
     * This is used to display the "form" for entering absent/present students.
     *
     * @return A list of all Student objects.
     * @throws IllegalStateException if no administrator is logged in.
     */
    public List<Student> getAllStudents() {
        if (!isLoggedIn()) {
            throw new IllegalStateException("Administrator must be logged in to retrieve student list.");
        }
        return new ArrayList<>(students.values());
    }

    /**
     * Enters absence data for multiple students on the currently selected date.
     * This simulates the "Fill out the form by entering absent students / present and click 'Save'" step.
     * For each student marked as absent or delayed, an Absence record is created and stored,
     * and a notification email is sent to the parent.
     *
     * @param studentAbsenceData A map where the key is studentId and the value is an AbsenceEntry.
     *                           AbsenceEntry contains the AbsenceType (ABSENCE, DELAY, or PRESENT)
     *                           and an optional reason.
     * @return A list of Absence objects that were successfully recorded.
     * @throws IllegalStateException if no administrator is logged in or no date is selected.
     * @throws IllegalArgumentException if studentAbsenceData is null.
     */
    public List<Absence> enterAbsenceData(Map<String, AbsenceEntry> studentAbsenceData) {
        if (!isLoggedIn()) {
            throw new IllegalStateException("Administrator must be logged in to enter absence data.");
        }
        if (selectedDate == null) {
            throw new IllegalStateException("A date must be selected before entering absence data.");
        }
        if (studentAbsenceData == null) {
            throw new IllegalArgumentException("Student absence data cannot be null.");
        }

        List<Absence> recordedAbsences = new ArrayList<>();
        System.out.println("\n--- Processing Absence Data for " + selectedDate + " ---");

        for (Map.Entry<String, AbsenceEntry> entry : studentAbsenceData.entrySet()) {
            String studentId = entry.getKey();
            AbsenceEntry absenceEntry = entry.getValue();

            Student student = students.get(studentId);
            if (student == null) {
                System.err.println("Warning: Student with ID '" + studentId + "' not found. Skipping.");
                continue;
            }

            // Only process if the student is marked as absent or delayed
            if (absenceEntry.getType() == AbsenceType.ABSENCE || absenceEntry.getType() == AbsenceType.DELAY) {
                Absence newAbsence = new Absence(student, selectedDate, absenceEntry.getType(), absenceEntry.getReason());

                // Check for duplicate entry (same student, same date, same type)
                boolean isDuplicate = absenceRecords.stream()
                    .anyMatch(a -> a.getStudent().equals(student) &&
                                   a.getDate().equals(selectedDate) &&
                                   a.getType().equals(absenceEntry.getType()));

                if (isDuplicate) {
                    System.out.println("Absence record for " + student.getName() + " on " + selectedDate + " (" + absenceEntry.getType() + ") already exists. Skipping.");
                    continue;
                }

                absenceRecords.add(newAbsence);
                recordedAbsences.add(newAbsence);
                System.out.println("Recorded: " + newAbsence);

                // Send notification for each absence
                notificationService.sendAbsenceNotification(student, newAbsence);
            } else {
                System.out.println(student.getName() + " (ID: " + studentId + ") is marked as PRESENT. No absence recorded.");
            }
        }
        System.out.println("--- Absence Data Processing Complete ---\n");
        return recordedAbsences;
    }

    /**
     * Retrieves all absence records stored in the system.
     * This simulates the "Displays the updated log data" step.
     *
     * @return A list of all Absence objects.
     * @throws IllegalStateException if no administrator is logged in.
     */
    public List<Absence> getAllAbsenceRecords() {
        if (!isLoggedIn()) {
            throw new IllegalStateException("Administrator must be logged in to view absence records.");
        }
        return new ArrayList<>(absenceRecords);
    }

    /**
     * Retrieves absence records for a specific date.
     *
     * @param date The date to filter absences by.
     * @return A list of Absence objects for the specified date.
     * @throws IllegalStateException if no administrator is logged in.
     * @throws IllegalArgumentException if the date is null.
     */
    public List<Absence> getAbsenceRecordsForDate(LocalDate date) {
        if (!isLoggedIn()) {
            throw new IllegalStateException("Administrator must be logged in to view absence records.");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null when retrieving absence records.");
        }
        return absenceRecords.stream()
                .filter(absence -> absence.getDate().equals(date))
                .collect(Collectors.toList());
    }

    /**
     * Helper class to encapsulate absence type and reason for data entry.
     */
    public static class AbsenceEntry {
        private final AbsenceType type;
        private final String reason;

        public AbsenceEntry(AbsenceType type, String reason) {
            if (type == null) {
                throw new IllegalArgumentException("Absence type cannot be null.");
            }
            this.type = type;
            this.reason = reason;
        }

        public AbsenceType getType() {
            return type;
        }

        public String getReason() {
            return reason;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AbsenceEntry that = (AbsenceEntry) o;
            return type == that.type && Objects.equals(reason, that.reason);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, reason);
        }

        @Override
        public String toString() {
            return "AbsenceEntry{" +
                   "type=" + type +
                   ", reason='" + (reason != null ? reason : "N/A") + '\'' +
                   '}';
        }
    }
}