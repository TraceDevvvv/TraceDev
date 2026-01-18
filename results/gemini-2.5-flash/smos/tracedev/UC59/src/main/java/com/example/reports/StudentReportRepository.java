package com.example.reports;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Concrete implementation of IStudentReportRepository, simulating a data source.
 * This class provides mock data and can simulate server connection errors.
 * Added to satisfy requirement ExC2.
 */
public class StudentReportRepository implements IStudentReportRepository {

    // A flag to simulate server connection errors for demonstration purposes.
    private boolean simulateServerConnectionError = false;

    // In-memory mock data store for student reports
    private Map<String, List<StudentReport>> studentReportsByStudentId;
    private Map<String, StudentReport> allReportsById;

    /**
     * Constructs a new StudentReportRepository and initializes it with mock data.
     */
    public StudentReportRepository() {
        initializeMockData();
    }

    /**
     * Sets the flag to simulate server connection errors.
     * @param simulateError true to simulate errors, false otherwise.
     */
    public void setSimulateServerConnectionError(boolean simulateError) {
        this.simulateServerConnectionError = simulateError;
    }

    /**
     * Initializes mock student report data for demonstration.
     */
    private void initializeMockData() {
        studentReportsByStudentId = new HashMap<>();
        allReportsById = new HashMap<>();

        // Create some mock report cards
        ReportCard rc1 = new ReportCard("rc001", "Mathematics", "A", "Excellent progress.");
        ReportCard rc2 = new ReportCard("rc002", "Physics", "B+", "Good understanding.");
        ReportCard rc3 = new ReportCard("rc003", "Chemistry", "A-", "Consistent effort.");
        ReportCard rc4 = new ReportCard("rc004", "History", "B", "Participation needed.");
        ReportCard rc5 = new ReportCard("rc005", "English", "C+", "Improve writing skills.");

        // Create some mock student reports for student "S101"
        StudentReport sr1 = new StudentReport("rep001", "S101", new Date(), Arrays.asList(rc1, rc2));
        StudentReport sr2 = new StudentReport("rep002", "S101", new Date(), Arrays.asList(rc3, rc4, rc5));

        // Create some mock student reports for student "S102"
        ReportCard rc6 = new ReportCard("rc006", "Biology", "A", "Outstanding!");
        ReportCard rc7 = new ReportCard("rc007", "Art", "B+", "Creative ideas.");
        StudentReport sr3 = new StudentReport("rep003", "S102", new Date(), Arrays.asList(rc6, rc7));

        studentReportsByStudentId.put("S101", Arrays.asList(sr1, sr2));
        studentReportsByStudentId.put("S102", Arrays.asList(sr3));

        allReportsById.put(sr1.getReportId(), sr1);
        allReportsById.put(sr2.getReportId(), sr2);
        allReportsById.put(sr3.getReportId(), sr3);
    }

    /**
     * {@inheritDoc}
     * Retrieves a list of all student reports for a given student ID from mock data.
     * Can throw ServerConnectionError based on the simulateServerConnectionError flag.
     */
    @Override
    public List<StudentReport> getReportsByStudentId(String studentId) throws ServerConnectionError {
        // Simulate a delay and potential error
        try {
            Thread.sleep(500); // Simulate network latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (simulateServerConnectionError) {
            throw new ServerConnectionError("Failed to connect to report server when getting reports for student " + studentId);
        }

        System.out.println("[Repository] Fetching reports for student: " + studentId);
        return studentReportsByStudentId.getOrDefault(studentId, new ArrayList<>());
    }

    /**
     * {@inheritDoc}
     * Retrieves a specific student report by its unique report ID from mock data.
     * Can throw ServerConnectionError based on the simulateServerConnectionError flag.
     */
    @Override
    public StudentReport getReportById(String reportId) throws ServerConnectionError {
        // Simulate a delay and potential error
        try {
            Thread.sleep(300); // Simulate network latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (simulateServerConnectionError) {
            throw new ServerConnectionError("Failed to connect to report server when getting report " + reportId);
        }

        System.out.println("[Repository] Fetching report by ID: " + reportId);
        return allReportsById.get(reportId);
    }
}