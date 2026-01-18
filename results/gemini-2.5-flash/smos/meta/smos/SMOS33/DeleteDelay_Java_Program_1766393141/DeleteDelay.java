import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * Represents a student in the system.
 */
class Student {
    private String studentId;
    private String name;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Student{id='" + studentId + "', name='" + name + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(studentId, student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}

/**
 * Represents a late entry for a student on a specific date.
 */
class LateEntry {
    private Student student;
    private LocalDate date;
    private String reason;

    public LateEntry(Student student, LocalDate date, String reason) {
        this.student = student;
        this.date = date;
        this.reason = reason;
    }

    public Student getStudent() {
        return student;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "LateEntry{student=" + student.getName() + ", date=" + date + ", reason='" + reason + "'}";
    }
}

/**
 * Manages the archive of late entries.
 */
class LateEntryArchive {
    // Stores late entries, keyed by date for efficient retrieval
    private Map<LocalDate, List<LateEntry>> archive;

    public LateEntryArchive() {
        this.archive = new HashMap<>();
    }

    /**
     * Adds a late entry to the archive.
     * @param entry The LateEntry object to add.
     */
    public void addLateEntry(LateEntry entry) {
        archive.computeIfAbsent(entry.getDate(), k -> new ArrayList<>()).add(entry);
        System.out.println("Late entry added for " + entry.getStudent().getName() + " on " + entry.getDate());
    }

    /**
     * Retrieves all late entries for a given date.
     * @param date The date to search for.
     * @return A list of LateEntry objects for the specified date, or an empty list if none exist.
     */
    public List<LateEntry> getLateEntriesByDate(LocalDate date) {
        return archive.getOrDefault(date, new ArrayList<>());
    }

    /**
     * Deletes a specific late entry from the archive.
     * @param date The date of the late entry.
     * @param studentId The ID of the student associated with the late entry.
     * @return true if the entry was successfully deleted, false otherwise.
     */
    public boolean deleteLateEntry(LocalDate date, String studentId) {
        List<LateEntry> entriesOnDate = archive.get(date);
        if (entriesOnDate != null) {
            boolean removed = entriesOnDate.removeIf(entry -> entry.getStudent().getStudentId().equals(studentId));
            if (entriesOnDate.isEmpty()) {
                archive.remove(date); // Remove date entry if no more late entries for that date
            }
            return removed;
        }
        return false;
    }

    /**
     * Displays all late entries in the archive.
     */
    public void displayAllEntries() {
        if (archive.isEmpty()) {
            System.out.println("Archive is empty.");
            return;
        }
        System.out.println("\n--- Late Entry Archive ---");
        archive.forEach((date, entries) -> {
            System.out.println("Date: " + date);
            entries.forEach(entry -> System.out.println("  - " + entry));
        });
        System.out.println("--------------------------");
    }
}

/**
 * Represents an Administrator user.
 */
class Administrator {
    private String username;
    private String password;

    public Administrator(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Authenticates the administrator.
     * @param inputUsername The username entered by the user.
     * @param inputPassword The password entered by the user.
     * @return true if credentials match, false otherwise.
     */
    public boolean login(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }
}

/**
 * Main class for the DeleteDelay application.
 * Simulates the process of an administrator eliminating a delay.
 */
public class DeleteDelay {

    private static Administrator currentAdmin = new Administrator("admin", "admin123");
    private static LateEntryArchive archive = new LateEntryArchive();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Delay Elimination System (DeleteDelay)");

        // Precondition: Administrator login
        if (!adminLogin()) {
            System.out.println("Login failed. Exiting system.");
            return;
        }
        System.out.println("Login successful. Welcome, Administrator!");

        // Simulate some initial late entries for demonstration
        seedInitialData();
        archive.displayAllEntries();

        // Main loop for delay elimination
        while (true) {
            System.out.println("\nRegistry Screen - Select an option:");
            System.out.println("1. Eliminate a delay");
            System.out.println("2. View all late entries");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    eliminateDelayProcess();
                    break;
                case "2":
                    archive.displayAllEntries();
                    break;
                case "3":
                    System.out.println("Administrator interrupts the operation. Exiting system.");
                    // Simulate connection to SMOS server interrupted
                    System.out.println("Connection to the SMOS server interrupted.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Handles the administrator login process.
     * @return true if login is successful, false otherwise.
     */
    private static boolean adminLogin() {
        System.out.print("Enter administrator username: ");
        String username = scanner.nextLine();
        System.out.print("Enter administrator password: ");
        String password = scanner.nextLine();
        return currentAdmin.login(username, password);
    }

    /**
     * Simulates the process of eliminating a delay.
     */
    private static void eliminateDelayProcess() {
        System.out.println("\n--- Eliminate Delay ---");
        System.out.print("Enter the date of the delay to eliminate (YYYY-MM-DD): ");
        String dateString = scanner.nextLine();
        LocalDate selectedDate;

        try {
            selectedDate = LocalDate.parse(dateString);
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }

        // Event 1: Update the screen based on the selected date
        List<LateEntry> entriesOnSelectedDate = archive.getLateEntriesByDate(selectedDate);
        if (entriesOnSelectedDate.isEmpty()) {
            System.out.println("No late entries found for " + selectedDate);
            return;
        }

        System.out.println("Late entries for " + selectedDate + ":");
        for (int i = 0; i < entriesOnSelectedDate.size(); i++) {
            System.out.println((i + 1) + ". " + entriesOnSelectedDate.get(i).getStudent().getName() + " (ID: " + entriesOnSelectedDate.get(i).getStudent().getStudentId() + ")");
        }

        System.out.print("Enter the student ID to remove late entry for (or 'cancel' to go back): ");
        String studentIdToRemove = scanner.nextLine();

        if (studentIdToRemove.equalsIgnoreCase("cancel")) {
            System.out.println("Operation cancelled. Returning to registry screen.");
            return;
        }

        // Event 2: Remove the late input of a student and click "Save"
        // (Simulated by calling deleteLateEntry and confirming)
        boolean studentFound = entriesOnSelectedDate.stream()
                                .anyMatch(entry -> entry.getStudent().getStudentId().equals(studentIdToRemove));

        if (!studentFound) {
            System.out.println("Student with ID " + studentIdToRemove + " not found for this date.");
            return;
        }

        System.out.print("Confirm removal of late entry for student ID " + studentIdToRemove + " on " + selectedDate + "? (yes/no): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            // Event 3: Delete late entry from the archive.
            if (archive.deleteLateEntry(selectedDate, studentIdToRemove)) {
                System.out.println("Late entry for student ID " + studentIdToRemove + " on " + selectedDate + " successfully eliminated.");
                // Postcondition: The system has eliminated the delay. The system remains on the registry screen.
                System.out.println("System remains on the registry screen.");
            } else {
                System.out.println("Failed to eliminate late entry. It might have already been removed or never existed.");
            }
        } else {
            System.out.println("Removal cancelled. Returning to registry screen.");
        }
    }

    /**
     * Seeds the archive with some initial data for testing purposes.
     */
    private static void seedInitialData() {
        Student s1 = new Student("S001", "Alice Smith");
        Student s2 = new Student("S002", "Bob Johnson");
        Student s3 = new Student("S003", "Charlie Brown");

        archive.addLateEntry(new LateEntry(s1, LocalDate.of(2023, 11, 15), "Overslept"));
        archive.addLateEntry(new LateEntry(s2, LocalDate.of(2023, 11, 15), "Traffic"));
        archive.addLateEntry(new LateEntry(s3, LocalDate.of(2023, 11, 16), "Doctor's appointment"));
        archive.addLateEntry(new LateEntry(s1, LocalDate.of(2023, 11, 16), "Car trouble"));
    }
}