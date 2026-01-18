'''
SMOSServer.java
A mock server class to simulate interactions with a School Management Online System (SMOS).
It provides dummy data for students and their report cards.
'''
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * SMOSServer.java
 * A mock server class to simulate interactions with a School Management Online System (SMOS).
 * It provides dummy data for students and their report cards.
 */
public class SMOSServer {
    // Dummy data: Maps parent ID to a list of their children (Student objects).
    private static final Map<String, List<Student>> PARENT_TO_STUDENTS = new HashMap<>();
    static {
        // Parent "parent123" has two children.
        PARENT_TO_STUDENTS.put("parent123", Arrays.asList(
            new Student("S001", "Alice Smith"),
            new Student("S002", "Bob Johnson")
        ));
        // Another dummy parent for testing.
        PARENT_TO_STUDENTS.put("parent456", Collections.singletonList(
            new Student("S003", "Charlie Brown")
        ));
    }
    // Dummy data: Maps student ID to a list of their ReportCard objects.
    private static final Map<String, List<ReportCard>> STUDENT_TO_REPORT_CARDS = new HashMap<>();
    static {
        // Alice Smith's (S001) report cards.
        Map<String, String> aliceGradesFall2023 = new HashMap<>();
        aliceGradesFall2023.put("Math", "A");
        aliceGradesFall2023.put("Science", "B+");
        aliceGradesFall2023.put("English", "A-");
        aliceGradesFall2023.put("History", "B");
        ReportCard aliceFall2023 = new ReportCard("S001", "Fall 2023", aliceGradesFall2023);
        Map<String, String> aliceGradesSpring2024 = new HashMap<>();
        aliceGradesSpring2024.put("Math", "B+");
        aliceGradesSpring2024.put("Science", "A-");
        aliceGradesSpring2024.put("English", "A");
        aliceGradesSpring2024.put("Art", "C+");
        ReportCard aliceSpring2024 = new ReportCard("S001", "Spring 2024", aliceGradesSpring2024);
        STUDENT_TO_REPORT_CARDS.put("S001", Arrays.asList(aliceFall2023, aliceSpring2024));
        // Bob Johnson's (S002) report cards.
        Map<String, String> bobGradesQ12024 = new HashMap<>();
        bobGradesQ12024.put("Math", "C");
        bobGradesQ12024.put("PE", "A"); // Fix: Corrected typo from bobGradesQ112024 to bobGradesQ12024
        bobGradesQ12024.put("Music", "B-");
        ReportCard bobQ12024 = new ReportCard("S002", "Q1 2024", bobGradesQ12024);
        STUDENT_TO_REPORT_CARDS.put("S002", Collections.singletonList(bobQ12024));
        // Charlie Brown (S003) has no report cards yet.
        STUDENT_TO_REPORT_CARDS.put("S003", new ArrayList<>());
    }
    /**
     * Simulates fetching a list of students associated with a given parent ID.
     * This method represents the first step after a parent logs in.
     *
     * @param parentId The ID of the parent.
     * @return A list of Student objects. Returns an empty list if the parent is not found or has no children.
     */
    public List<Student> fetchStudentsForParent(String parentId) {
        System.out.println("SMOS Server: Fetching students for parent ID: " + parentId);
        try {
            Thread.sleep(500); // Simulate network latency for data fetching.
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status.
            System.err.println("SMOS Server: Fetch students interrupted.");
        }
        return PARENT_TO_STUDENTS.getOrDefault(parentId, Collections.emptyList());
    }
    /**
     * Simulates fetching a list of report cards for a given student ID.
     * This method represents the "View selected student report cards" event.
     *
     * @param studentId The ID of the student.
     * @return A list of ReportCard objects. Returns an empty list if the student or their reports are not found.
     */
    public List<ReportCard> fetchReportCardsForStudent(String studentId) {
        System.out.println("SMOS Server: Fetching report cards for student ID: " + studentId);
        try {
            Thread.sleep(700); // Simulate network latency for data fetching.
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status.
            System.err.println("SMOS Server: Fetch report cards interrupted.");
        }
        return STUDENT_TO_REPORT_CARDS.getOrDefault(studentId, Collections.emptyList());
    }
    /**
     * Simulates disconnecting from the SMOS server.
     * This fulfills the postcondition "Connection to the interrupted SMOS server".
     * In a real application, this would involve closing open connections, clearing session data, etc.
     */
    public void disconnect() {
        System.out.println("SMOS Server: Connection interrupted (simulated).");
    }
}