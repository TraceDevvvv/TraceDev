import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main program for the "ViewNotesList" use case.
 * This program allows an administrator to view all notes for a selected student.
 * It demonstrates authentication, student selection, note retrieval, and display.
 * The program simulates a school management system with a simple console interface.
 */
public class ViewNotesList {

    /**
     * Represents a note for a student.
     * Each note has an ID, the student's ID, the date the note was recorded,
     * the teacher who wrote it, and the note content.
     */
    static class Note {
        private int id;
        private int studentId;
        private LocalDate date;
        private String teacher;
        private String content;

        public Note(int id, int studentId, LocalDate date, String teacher, String content) {
            this.id = id;
            this.studentId = studentId;
            this.date = date;
            this.teacher = teacher;
            this.content = content;
        }

        // Getters
        public int getId() { return id; }
        public int getStudentId() { return studentId; }
        public LocalDate getDate() { return date; }
        public String getTeacher() { return teacher; }
        public String getContent() { return content; }

        @Override
        public String toString() {
            return "Note ID: " + id + " | Student ID: " + studentId +
                   " | Date: " + date + " | Teacher: " + teacher +
                   " | Content: " + content;
        }
    }

    /**
     * Represents a student in the school.
     */
    static class Student {
        private int id;
        private String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() { return id; }
        public String getName() { return name; }

        @Override
        public String toString() {
            return "Student ID: " + id + " | Name: " + name;
        }
    }

    /**
     * Represents an administrator user in the system.
     * The administrator must be logged in to perform operations.
     */
    static class Administrator {
        private String username;
        private boolean isLoggedIn;

        public Administrator(String username) {
            this.username = username;
            this.isLoggedIn = false;
        }

        /**
         * Simulates login with a password.
         * For simplicity, any non-empty password is accepted.
         * In a real system, proper authentication would be required.
         */
        public void login(String password) {
            if (password == null || password.trim().isEmpty()) {
                System.out.println("✗ Login failed: password cannot be empty.");
                return;
            }
            isLoggedIn = true;
            System.out.println("✓ Administrator " + username + " logged in successfully.");
        }

        public void logout() {
            if (isLoggedIn) {
                isLoggedIn = false;
                System.out.println("✓ Administrator " + username + " logged out.");
            } else {
                System.out.println("✗ No active session.");
            }
        }

        public boolean isLoggedIn() { return isLoggedIn; }
        public String getUsername() { return username; }
    }

    /**
     * Simulates a backend that holds student and note data.
     * In a real application, this would be replaced with a database.
     */
    static class SchoolDataService {
        private List<Student> students;
        private List<Note> notes;

        public SchoolDataService() {
            initializeSampleData();
        }

        /**
         * Creates sample data for demonstration.
         * Four sample students and some notes for each.
         */
        private void initializeSampleData() {
            students = new ArrayList<>();
            students.add(new Student(1, "Alice Johnson"));
            students.add(new Student(2, "Bob Smith"));
            students.add(new Student(3, "Carol Davis"));
            students.add(new Student(4, "David Wilson"));

            notes = new ArrayList<>();
            LocalDate currentDate = LocalDate.now();
            // Notes for student 1
            notes.add(new Note(101, 1, currentDate.minusDays(10), "Mr. Brown", "Alice is doing well in mathematics."));
            notes.add(new Note(102, 1, currentDate.minusDays(5), "Ms. Green", "Alice participated actively in science lab."));
            // Notes for student 2
            notes.add(new Note(201, 2, currentDate.minusDays(15), "Mr. Brown", "Bob needs to improve homework submission."));
            notes.add(new Note(202, 2, currentDate.minusDays(3), "Ms. Green", "Bob showed great improvement in the last test."));
            // Notes for student 3
            notes.add(new Note(301, 3, currentDate.minusDays(20), "Mr. White", "Carol is excelling in literature."));
            // Notes for student 4
            notes.add(new Note(401, 4, currentDate.minusDays(1), "Ms. Green", "David was absent due to illness."));
            notes.add(new Note(402, 4, currentDate.minusDays(8), "Mr. White", "David's project was submitted on time."));
            notes.add(new Note(403, 4, currentDate.minusDays(12), "Mr. Brown", "David needs more practice in algebra."));
        }

        public List<Student> getAllStudents() {
            // Return a copy to prevent external modification
            return new ArrayList<>(students);
        }

        /**
         * Returns all notes for a specific student, recorded during the current school year.
         * The school year is assumed to start on September 1 of the previous year.
         * Only notes from the current school year are shown.
         */
        public List<Note> getNotesForStudent(int studentId) {
            List<Note> result = new ArrayList<>();
            LocalDate today = LocalDate.now();
            // Determine current school year start (September 1 of the previous year if after Jan 1 and before Sep 1)
            int year = today.getYear();
            int month = today.getMonthValue();
            LocalDate schoolYearStart;
            if (month >= 9) {
                // School year started September of this year
                schoolYearStart = LocalDate.of(year, 9, 1);
            } else {
                // School year started September of last year
                schoolYearStart = LocalDate.of(year - 1, 9, 1);
            }

            for (Note note : notes) {
                if (note.getStudentId() == studentId && !note.getDate().isBefore(schoolYearStart)) {
                    result.add(note);
                }
            }
            return result;
        }

        /**
         * Simulates interrupting the connection to the SMOS server.
         * This could represent a network error or intentional disconnection.
         * In a real system, this would involve closing network connections or sessions.
         */
        public void interruptSMOSConnection() {
            System.out.println("\n⚠ Interrupting connection to SMOS server...");
            System.out.println("✓ SMOS server connection interrupted.");
        }
    }

    /**
     * Main method to run the program.
     * Steps:
     * 1. Administrator logs in.
     * 2. Display list of students.
     * 3. Administrator selects a student (simulating clicking the "Notes" button).
     * 4. Display all notes for that student from the current school year.
     * 5. Optionally interrupt connection (as per postcondition).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Administrator admin = new Administrator("admin1");
        SchoolDataService dataService = new SchoolDataService();

        System.out.println("=== School Management System - View Notes List ===");
        System.out.println();

        // 1. Administrator login
        System.out.print("Enter administrator password (any non-empty password): ");
        String password = scanner.nextLine();
        admin.login(password);
        if (!admin.isLoggedIn()) {
            System.out.println("Access denied. Exiting.");
            scanner.close();
            return;
        }

        // 2. Display all students (simulating the result of 'SviewTetTingloregister')
        System.out.println("\n--- List of Students ---");
        List<Student> students = dataService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found in the system.");
        } else {
            for (int i = 0; i < students.size(); i++) {
                System.out.println((i + 1) + ". " + students.get(i));
            }
        }

        // 3. Choose a student (simulating clicking the "Notes" button)
        System.out.print("\nEnter the number of the student to view notes (or 0 to exit): ");
        int choice;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Exiting.");
            admin.logout();
            scanner.close();
            return;
        }
        if (choice == 0) {
            System.out.println("Exiting without viewing notes.");
            admin.logout();
            scanner.close();
            return;
        }
        if (choice < 1 || choice > students.size()) {
            System.out.println("Invalid student number. Exiting.");
            admin.logout();
            scanner.close();
            return;
        }
        Student selectedStudent = students.get(choice - 1);
        System.out.println("\nSelected student: " + selectedStudent.getName());

        // 4. Retrieve and display notes for the selected student
        System.out.println("\n--- Notes for " + selectedStudent.getName() + " (current school year) ---");
        List<Note> studentNotes = dataService.getNotesForStudent(selectedStudent.getId());
        if (studentNotes.isEmpty()) {
            System.out.println("No notes found for this student in the current school year.");
        } else {
            studentNotes.forEach(System.out::println);
        }

        // 5. Interrupt connection to SMOS server (postcondition)
        System.out.print("\nInterrupt connection to SMOS server? (yes/no): ");
        String interruptChoice = scanner.nextLine();
        if (interruptChoice != null && interruptChoice.equalsIgnoreCase("yes")) {
            dataService.interruptSMOSConnection();
        } else {
            System.out.println("Connection remains active.");
        }

        // 6. Logout and clean up
        admin.logout();
        scanner.close();
        System.out.println("\n=== Program ended ===");
    }
}