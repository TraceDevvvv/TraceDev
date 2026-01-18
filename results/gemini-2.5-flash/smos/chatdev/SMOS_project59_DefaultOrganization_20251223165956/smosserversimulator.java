import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
 * Simulates a Simple Management and Operations System (SMOS) server connection
 * for retrieving student report data.
 * This class provides mock data as specified by the use case.
 */
public class SMOSServerSimulator {
    // Mock data storage for students and their report cards
    // Key: studentId, Value: List of ReportCard objects for that student
    private Map<String, List<ReportCard>> studentReportsArchive;
    // Key: reportId, Value: ReportCard object for quick lookup
    private Map<String, ReportCard> allReportCards;
    private volatile boolean isConnectionActive = true; // volatile to ensure visibility across threads
    /**
     * Constructs the SMOSServerSimulator and initializes it with mock data.
     * This simulates a database or server-side data store.
     */
    public SMOSServerSimulator() {
        studentReportsArchive = new HashMap<>();
        allReportCards = new HashMap<>();
        initializeMockData();
    }
    /**
     * Populates the simulator with predefined student and report card data.
     * This simulates data existing in the SMOS server's archive.
     */
    private void initializeMockData() {
        // Create mock report cards for a specific student (e.g., S101)
        List<SubjectGrade> grades1 = Arrays.asList(
            new SubjectGrade("Mathematics", "A"),
            new SubjectGrade("Physics", "B+"),
            new SubjectGrade("Chemistry", "A-"),
            new SubjectGrade("English Literature", "B")
        );
        ReportCard rc1 = new ReportCard("RC001", "S101", "Fall 2023 Semester", grades1);
        List<SubjectGrade> grades2 = Arrays.asList(
            new SubjectGrade("Calculus II", "A"),
            new SubjectGrade("Object-Oriented Programming", "A+"),
            new SubjectGrade("Data Structures", "A-"),
            new SubjectGrade("Digital Logic Design", "B+")
        );
        ReportCard rc2 = new ReportCard("RC002", "S101", "Spring 2024 Semester", grades2);
        List<SubjectGrade> grades3 = Arrays.asList(
            new SubjectGrade("Linear Algebra", "A-"),
            new SubjectGrade("Discrete Mathematics", "B+"),
            new SubjectGrade("Operating Systems", "A"),
            new SubjectGrade("Computer Architecture", "B")
        );
        ReportCard rc3 = new ReportCard("RC003", "S101", "Summer 2024 Term", grades3);
        // Add reports for student S101
        List<ReportCard> s101Reports = new ArrayList<>();
        s101Reports.add(rc1);
        s101Reports.add(rc2);
        s101Reports.add(rc3);
        studentReportsArchive.put("S101", s101Reports);
        // Add all report cards to the general lookup map
        allReportCards.put(rc1.getReportId(), rc1);
        allReportCards.put(rc2.getReportId(), rc2);
        allReportCards.put(rc3.getReportId(), rc3);
    }
    /**
     * Retrieves a list of all report cards for a given student ID.
     * Simulates fetching data from the SMOS server.
     *
     * @param studentId The ID of the student whose reports are to be retrieved.
     * @return An unmodifiable list of ReportCard objects. Returns an empty list
     *         if the student ID is not found or if the connection is inactive.
     */
    public List<ReportCard> getStudentReports(String studentId) {
        if (!isConnectionActive) {
            System.out.println("SMOS Server: Connection is interrupted. Cannot retrieve reports.");
            return Collections.emptyList();
        }
        System.out.println("SMOS Server: Fetching reports for student: " + studentId);
        List<ReportCard> reports = studentReportsArchive.getOrDefault(studentId, Collections.emptyList());
        // Simulate small delay for network latency
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.err.println("SMOS Server: Report fetching interrupted: " + e.getMessage());
            return Collections.emptyList(); // Return empty list on interruption
        }
        System.out.println("SMOS Server: Reports fetched.");
        return Collections.unmodifiableList(reports);
    }
    /**
     * Retrieves the detailed information for a specific report card ID.
     * Simulates fetching data from the SMOS server.
     *
     * @param reportId The unique ID of the report card to retrieve.
     * @return The ReportCard object if found, null otherwise or if connection is inactive.
     */
    public ReportCard getReportCardDetails(String reportId) {
        if (!isConnectionActive) {
            System.out.println("SMOS Server: Connection is interrupted. Cannot retrieve report details.");
            return null;
        }
        System.out.println("SMOS Server: Fetching details for report: " + reportId);
        ReportCard report = allReportCards.get(reportId);
        // Simulate small delay for network latency
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.err.println("SMOS Server: Report details fetching interrupted: " + e.getMessage());
            return null; // Return null on interruption
        }
        System.out.println("SMOS Server: Report details fetched.");
        return report;
    }
    /**
     * Simulates the interruption of the connection to the SMOS server.
     * As per postcondition: "Connection to the SMOS server interrupted".
     * In a real system, this might involve closing network sockets or releasing resources.
     * For this simulation, it simply marks the connection as inactive.
     */
    public void interruptConnection() {
        this.isConnectionActive = false;
        System.out.println("SMOS Server: Connection to SMOS server interrupted.");
    }
    /**
     * Checks if the simulated SMOS server connection is active.
     * @return true if the connection is active, false otherwise.
     */
    public boolean isConnectionActive() {
        return isConnectionActive;
    }
}