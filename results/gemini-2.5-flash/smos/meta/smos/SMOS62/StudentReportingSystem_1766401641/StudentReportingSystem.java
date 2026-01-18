import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

/**
 * Main application class for the Student Reporting System.
 * This class simulates the interaction flow for a parent viewing a student's report card.
 * It includes data initialization, user login, student selection, report card selection,
 * and display of report card details.
 */
public class StudentReportingSystem {

    // --- Data Storage ---
    // In a real application, this data would come from a database or external service.
    private Map<String, Parent> parents; // Stores parents, keyed by username for easy lookup during login
    private Map<String, Student> students; // Stores students, keyed by studentId
    private Map<String, List<ReportCard>> reportCards; // Stores report cards, keyed by studentId

    // --- Current Session State ---
    private Parent loggedInParent; // Stores the currently logged-in parent

    /**
     * Constructs a new StudentReportingSystem and initializes its data structures.
     */
    public StudentReportingSystem() {
        this.parents = new HashMap<>();
        this.students = new HashMap<>();
        this.reportCards = new HashMap<>();
        this.loggedInParent = null; // No parent logged in initially
    }

    /**
     * Initializes sample data for parents, students, and report cards.
     * This method populates the system with predefined data for demonstration purposes.
     */
    private void initializeData() {
        // Create Students
        Student student1 = new Student("S001", "Alice Smith");
        Student student2 = new Student("S002", "Bob Johnson");
        Student student3 = new Student("S003", "Charlie Brown");

        students.put(student1.getStudentId(), student1);
        students.put(student2.getStudentId(), student2);
        students.put(student3.getStudentId(), student3);

        // Create Parents
        Parent parent1 = new Parent("P001", "John Smith", "johns", "pass123");
        parent1.addAssociatedStudent(student1.getStudentId()); // John Smith is parent of Alice
        parent1.addAssociatedStudent(student3.getStudentId()); // John Smith is also parent of Charlie

        Parent parent2 = new Parent("P002", "Jane Johnson", "janej", "securepwd");
        parent2.addAssociatedStudent(student2.getStudentId()); // Jane Johnson is parent of Bob

        parents.put(parent1.getUsername(), parent1);
        parents.put(parent2.getUsername(), parent2);

        // Create Report Cards for Student 1 (Alice)
        ReportCard rc1_s1 = new ReportCard(student1.getStudentId(), "Fall 2023", "Alice showed great improvement.");
        rc1_s1.addOrUpdateGrade("Math", "A-");
        rc1_s1.addOrUpdateGrade("Science", "B+");
        rc1_s1.addOrUpdateGrade("English", "A");

        ReportCard rc2_s1 = new ReportCard(student1.getStudentId(), "Spring 2024", "Excellent performance this semester!");
        rc2_s1.addOrUpdateGrade("Math", "A");
        rc2_s1.addOrUpdateGrade("Science", "A-");
        rc2_s1.addOrUpdateGrade("English", "A+");
        rc2_s1.addOrUpdateGrade("History", "B");

        reportCards.computeIfAbsent(student1.getStudentId(), k -> new ArrayList<>()).add(rc1_s1);
        reportCards.computeIfAbsent(student1.getStudentId(), k -> new ArrayList<>()).add(rc2_s1);

        // Create Report Cards for Student 2 (Bob)
        ReportCard rc1_s2 = new ReportCard(student2.getStudentId(), "Fall 2023", "Bob is a diligent student.");
        rc1_s2.addOrUpdateGrade("Math", "B");
        rc1_s2.addOrUpdateGrade("Physics", "C+");
        rc1_s2.addOrUpdateGrade("Chemistry", "B-");

        reportCards.computeIfAbsent(student2.getStudentId(), k -> new ArrayList<>()).add(rc1_s2);

        // Create Report Cards for Student 3 (Charlie)
        ReportCard rc1_s3 = new ReportCard(student3.getStudentId(), "Fall 2023", "Charlie needs to focus more in class.");
        rc1_s3.addOrUpdateGrade("Art", "A");
        rc1_s3.addOrUpdateGrade("Music", "B+");

        reportCards.computeIfAbsent(student3.getStudentId(), k -> new ArrayList<>()).add(rc1_s3);

        System.out.println("Sample data initialized.");
    }

    /**
     * Simulates the login process for a parent.
     *
     * @param username The username entered by the parent.
     * @param password The password entered by the parent.
     * @return true if login is successful, false otherwise.
     */
    public boolean login(String username, String password) {
        Parent parent = parents.get(username);
        if (parent != null && parent.authenticate(username, password)) {
            this.loggedInParent = parent;
            System.out.println("Login successful. Welcome, " + parent.getName() + "!");
            return true;
        } else {
            System.out.println("Login failed. Invalid username or password.");
            return false;
        }
    }

    /**
     * Retrieves a list of Student objects associated with the currently logged-in parent.
     *
     * @return A list of Student objects, or an empty list if no parent is logged in
     *         or no students are associated.
     */
    public List<Student> getAssociatedStudents() {
        if (loggedInParent == null) {
            return Collections.emptyList();
        }

        List<Student> associatedStudents = new ArrayList<>();
        for (String studentId : loggedInParent.getAssociatedStudentIds()) {
            Student student = students.get(studentId);
            if (student != null) {
                associatedStudents.add(student);
            }
        }
        return associatedStudents;
    }

    /**
     * Retrieves a list of ReportCard objects for a given student ID.
     *
     * @param studentId The ID of the student whose report cards are to be retrieved.
     * @return A list of ReportCard objects, or an empty list if no report cards are found
     *         for the given student ID.
     */
    public List<ReportCard> getReportCardsForStudent(String studentId) {
        return reportCards.getOrDefault(studentId, Collections.emptyList());
    }

    /**
     * Displays the details of a given report card.
     *
     * @param reportCard The ReportCard object to display.
     */
    public void displayReportCardDetails(ReportCard reportCard) {
        if (reportCard == null) {
            System.out.println("No report card details to display.");
            return;
        }
        System.out.println("\n" + reportCard.toString());
    }

    /**
     * Simulates the postcondition: "Connection to the interrupted SMOS server".
     * In a real system, this might involve logging, attempting to reconnect, or
     * displaying a user-facing message about system status.
     */
    public void simulateSMOSConnectionInterruption() {
        System.out.println("\n--- System Notification ---");
        System.out.println("Connection to the SMOS server was interrupted.");
        System.out.println("Please note that some real-time data or updates might be unavailable.");
        System.out.println("---------------------------\n");
    }

    /**
     * The main entry point for the Student Reporting System application.
     * This method orchestrates the user interaction flow.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        StudentReportingSystem system = new StudentReportingSystem();
        system.initializeData(); // Populate with sample data
        Scanner scanner = new Scanner(System.in);

        // Precondition: The user has been logged in to the system as a parent
        // Simulate login
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            loggedIn = system.login(username, password);
            if (!loggedIn) {
                System.out.println("Please try again.");
            }
        }

        // Precondition: The user clicks on the Magine button associated with one of his children
        // Event 1: View selected student report cards (implicitly, by selecting a child first)
        List<Student> associatedStudents = system.getAssociatedStudents();

        if (associatedStudents.isEmpty()) {
            System.out.println("You have no students associated with your account.");
            scanner.close();
            return;
        }

        Student selectedStudent = null;
        while (selectedStudent == null) {
            System.out.println("\nYour Associated Children:");
            for (int i = 0; i < associatedStudents.size(); i++) {
                System.out.println((i + 1) + ". " + associatedStudents.get(i).getName() + " (ID: " + associatedStudents.get(i).getStudentId() + ")");
            }
            System.out.print("Select a child to view their reports (enter number): ");
            String choice = scanner.nextLine();
            try {
                int studentIndex = Integer.parseInt(choice) - 1;
                if (studentIndex >= 0 && studentIndex < associatedStudents.size()) {
                    selectedStudent = associatedStudents.get(studentIndex);
                    System.out.println("Selected student: " + selectedStudent.getName());
                } else {
                    System.out.println("Invalid selection. Please enter a valid number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        // Event 2: Select the report card
        List<ReportCard> studentReportCards = system.getReportCardsForStudent(selectedStudent.getStudentId());

        if (studentReportCards.isEmpty()) {
            System.out.println("No report cards found for " + selectedStudent.getName() + ".");
            scanner.close();
            system.simulateSMOSConnectionInterruption(); // Postcondition
            return;
        }

        ReportCard chosenReportCard = null;
        while (chosenReportCard == null) {
            System.out.println("\nReport Cards for " + selectedStudent.getName() + ":");
            for (int i = 0; i < studentReportCards.size(); i++) {
                System.out.println((i + 1) + ". " + studentReportCards.get(i).getPeriod());
            }
            System.out.print("Select a report card to view details (enter number): ");
            String choice = scanner.nextLine();
            try {
                int rcIndex = Integer.parseInt(choice) - 1;
                if (rcIndex >= 0 && rcIndex < studentReportCards.size()) {
                    chosenReportCard = studentReportCards.get(rcIndex);
                } else {
                    System.out.println("Invalid selection. Please enter a valid number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        // Event 3: Displays the details of the selected report card
        // Postcondition: The system displays a student's reporting
        system.displayReportCardDetails(chosenReportCard);

        // Postcondition: Connection to the interrupted SMOS server
        system.simulateSMOSConnectionInterruption();

        scanner.close();
        System.out.println("Exiting Student Reporting System. Goodbye!");
    }
}