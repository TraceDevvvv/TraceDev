'''
DatabaseSimulator.java
Simulates a database for storing student report cards and user credentials.
In a real application, this would connect to an actual database.
'''
import java.util.HashMap;
import java.util.Map;
public class DatabaseSimulator {
    private Map<String, String> parentCredentials; // Stores parent username-password pairs
    private Map<String, Map<String, ReportCard>> studentReports; // Maps parent to their children's reports
    public DatabaseSimulator() {
        parentCredentials = new HashMap<>();
        studentReports = new HashMap<>();
        initializeSampleData();
    }
    /**
     * Initializes the database with sample data for demonstration.
     */
    private void initializeSampleData() {
        // Add parent credentials
        parentCredentials.put("john_doe", "password123");
        parentCredentials.put("jane_smith", "secure456");
        // Create sample report cards for John Doe's children
        Map<String, ReportCard> johnChildren = new HashMap<>();
        johnChildren.put("Alice Doe", new ReportCard("Alice Doe", "Grade 5", "Spring 2024", 
            new String[]{"Math: A", "Science: B+", "English: A-", "History: A"}));
        johnChildren.put("Bob Doe", new ReportCard("Bob Doe", "Grade 3", "Spring 2024",
            new String[]{"Math: B", "Science: A-", "English: B+", "Art: A"}));
        studentReports.put("john_doe", johnChildren);
        // Create sample report cards for Jane Smith's children
        Map<String, ReportCard> janeChildren = new HashMap<>();
        janeChildren.put("Charlie Smith", new ReportCard("Charlie Smith", "Grade 7", "Spring 2024",
            new String[]{"Math: A+", "Science: A", "English: A", "Spanish: B+"}));
        studentReports.put("jane_smith", janeChildren);
    }
    /**
     * Validates parent login credentials.
     * @param username The parent's username
     * @param password The parent's password
     * @return true if credentials are valid, false otherwise
     */
    public boolean validateParent(String username, String password) {
        return parentCredentials.containsKey(username) && 
               parentCredentials.get(username).equals(password);
    }
    /**
     * Retrieves the list of children for a parent.
     * @param parentUsername The parent's username
     * @return Array of children names, or empty array if parent has no children
     */
    public String[] getChildrenForParent(String parentUsername) {
        if (studentReports.containsKey(parentUsername)) {
            Map<String, ReportCard> children = studentReports.get(parentUsername);
            return children.keySet().toArray(new String[0]);
        }
        return new String[0];
    }
    /**
     * Retrieves a specific report card for a child.
     * @param parentUsername The parent's username
     * @param childName The child's name
     * @return The ReportCard object, or null if not found
     */
    public ReportCard getReportCard(String parentUsername, String childName) {
        if (studentReports.containsKey(parentUsername)) {
            Map<String, ReportCard> children = studentReports.get(parentUsername);
            return children.get(childName);
        }
        return null;
    }
    /**
     * Simulates connection to SMOS server (Student Management and Operations System).
     * In a real implementation, this would establish a network connection.
     * @return true if connection successful, false if interrupted
     */
    public boolean connectToSMOSServer() {
        // Simulate occasional server interruptions
        double random = Math.random();
        if (random < 0.2) { // 20% chance of interruption
            System.err.println("SMOS Server connection interrupted. Please try again.");
            return false;
        }
        System.out.println("Successfully connected to SMOS Server.");
        return true;
    }
}
/**
 * ReportCard.java
 * Represents a student's report card with grades and information.
 */
class ReportCard {
    private String studentName;
    private String gradeLevel;
    private String semester;
    private String[] subjectsAndGrades;
    public ReportCard(String studentName, String gradeLevel, String semester, String[] subjectsAndGrades) {
        this.studentName = studentName;
        this.gradeLevel = gradeLevel;
        this.semester = semester;
        this.subjectsAndGrades = subjectsAndGrades;
    }
    public String getStudentName() { return studentName; }
    public String getGradeLevel() { return gradeLevel; }
    public String getSemester() { return semester; }
    public String[] getSubjectsAndGrades() { return subjectsAndGrades; }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Report Card for: ").append(studentName).append("\n");
        sb.append("Grade Level: ").append(gradeLevel).append("\n");
        sb.append("Semester: ").append(semester).append("\n");
        sb.append("Grades:\n");
        for (String subjectGrade : subjectsAndGrades) {
            sb.append("  - ").append(subjectGrade).append("\n");
        }
        return sb.toString();
    }
}