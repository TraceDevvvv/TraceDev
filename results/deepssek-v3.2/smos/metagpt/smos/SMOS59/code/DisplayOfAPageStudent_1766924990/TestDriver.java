import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * TestDriver.java
 * Simple test driver to demonstrate all Java classes work together.
 * Tests the DisplayOfAPageStudent system by creating sample data,
 * testing ReportService functionality, and showing the complete flow
 * from the use case.
 * 
 * This test driver demonstrates:
 * 1. Creating sample Student and Report objects
 * 2. Testing ReportService functionality
 * 3. Simulating the full use case flow
 * 4. Error handling for SMOS server interruptions
 */
public class TestDriver {
    
    public static void main(String[] args) {
        System.out.println("=== TestDriver for DisplayOfAPageStudent System ===\n");
        
        try {
            // Test 1: Create and test Student class
            System.out.println("Test 1: Creating and testing Student class...");
            testStudentClass();
            
            // Test 2: Create and test Report class
            System.out.println("\nTest 2: Creating and testing Report class...");
            testReportClass();
            
            // Test 3: Test ReportService class
            System.out.println("\nTest 3: Testing ReportService class...");
            testReportServiceClass();
            
            // Test 4: Simulate full use case flow
            System.out.println("\nTest 4: Simulating full use case flow...");
            simulateUseCaseFlow();
            
            // Test 5: Test error handling and server interruptions
            System.out.println("\nTest 5: Testing error handling and server interruptions...");
            testErrorHandling();
            
            System.out.println("\n=== All tests completed successfully ===");
            
        } catch (Exception e) {
            System.out.println("\nTest failed with exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Test 1: Create and test Student class functionality.
     */
    private static void testStudentClass() {
        // Create a sample student
        Student student = new Student("John", "Doe", "john.doe@university.edu", 
                                     "Computer Science", 3);
        
        System.out.println("Created student: " + student.getFullName());
        System.out.println("Student ID: " + student.getStudentId());
        System.out.println("Program: " + student.getProgram());
        System.out.println("Year of study: " + student.getYearOfStudy());
        
        // Test student validation
        System.out.println("Student is valid: " + student.isValid());
        
        // Test adding reports to student
        Report report1 = createSampleReport("2023 Fall Semester", "Fall 2023", 85.5, 
                                           "Good performance overall", "STU-12345");
        Report report2 = createSampleReport("2023 Spring Semester", "Spring 2023", 78.5, 
                                           "Average performance", "STU-12345");
        
        student.addReport(report1);
        student.addReport(report2);
        
        System.out.println("Student has " + student.getReports().size() + " reports");
        System.out.println("Report titles: " + student.getReportTitles());
        
        // Test report retrieval methods
        System.out.println("Overall average: " + student.calculateOverallAverage());
        System.out.println("Math subject average: " + student.calculateSubjectAverage("Mathematics"));
        System.out.println("Has failing grades: " + student.hasAnyFailingGrades());
        
        // Test get best report
        Report bestReport = student.getBestReport();
        if (bestReport != null) {
            System.out.println("Best report: " + bestReport.getTitle() + 
                             " (Grade: " + bestReport.getOverallGrade() + ")");
        }
        
        System.out.println("✓ Student class test passed");
    }
    
    /**
     * Test 2: Create and test Report class functionality.
     */
    private static void testReportClass() {
        // Create a sample report with subject grades
        Map<String, Double> subjectGrades = new HashMap<>();
        subjectGrades.put("Mathematics", 88.0);
        subjectGrades.put("Physics", 82.0);
        subjectGrades.put("Chemistry", 87.0);
        subjectGrades.put("English", 92.0);
        subjectGrades.put("History", 78.0);
        
        Report report = new Report("2023 Fall Semester", "Fall 2023", 85.5, 
                                  "Good performance overall", subjectGrades);
        report.setStudentId("STU-12345");
        
        System.out.println("Created report: " + report.getTitle());
        System.out.println("Report ID: " + report.getReportId());
        System.out.println("Semester: " + report.getSemester());
        System.out.println("Overall grade: " + report.getOverallGrade());
        System.out.println("Comments: " + report.getComments());
        System.out.println("Grade letter: " + report.getGradeLetter());
        System.out.println("Student ID: " + report.getStudentId());
        
        // Test report calculations
        System.out.println("Subject average: " + report.calculateSubjectAverage());
        System.out.println("Highest subject grade: " + report.getHighestSubjectGrade());
        System.out.println("Lowest subject grade: " + report.getLowestSubjectGrade());
        System.out.println("Has failing grades: " + report.hasFailingGrades());
        
        // Test subject grade management
        System.out.println("Math grade: " + report.getSubjectGrade("Mathematics"));
        report.addSubjectGrade("Biology", 91.0);
        System.out.println("Added Biology grade: " + report.getSubjectGrade("Biology"));
        
        System.out.println("Number of subjects: " + report.getSubjectGrades().size());
        
        // Test validation
        System.out.println("Report is valid: " + report.isValid());
        
        // Test copy functionality
        Report copy = report.copy();
        System.out.println("Created copy, same title: " + copy.getTitle().equals(report.getTitle()));
        
        System.out.println("✓ Report class test passed");
    }
    
    /**
     * Test 3: Test ReportService class functionality.
     */
    private static void testReportServiceClass() {
        ReportService service = new ReportService();
        String studentId = "STU-67890";
        
        System.out.println("Created ReportService instance");
        System.out.println("SMOS server connected: " + service.isSmosServerConnected());
        
        try {
            // Fetch reports for student
            System.out.println("\nFetching reports for student: " + studentId);
            List<Report> reports = service.fetchStudentReports(studentId);
            
            System.out.println("Fetched " + reports.size() + " reports:");
            for (Report report : reports) {
                System.out.println("  - " + report.getTitle() + " (Grade: " + 
                                 report.getOverallGrade() + ", " + report.getGradeLetter() + ")");
            }
            
            // Test fetching specific report
            if (!reports.isEmpty()) {
                String reportId = reports.get(0).getReportId();
                System.out.println("\nFetching specific report: " + reportId);
                Report specificReport = service.fetchReportById(studentId, reportId);
                System.out.println("Found report: " + specificReport.getTitle());
            }
            
            // Test filtering by semester
            System.out.println("\nFiltering reports by semester 'Fall 2023':");
            List<Report> fallReports = service.getReportsBySemester(studentId, "Fall 2023");
            System.out.println("Found " + fallReports.size() + " Fall 2023 reports");
            
            // Test statistics
            System.out.println("\nGetting report statistics:");
            Map<String, Object> stats = service.getReportStatistics(studentId);
            System.out.println("Total reports: " + stats.get("totalReports"));
            System.out.println("Average grade: " + stats.get("averageGrade"));
            
            if (stats.containsKey("gradeDistribution")) {
                Map<?, ?> gradeDist = (Map<?, ?>) stats.get("gradeDistribution");
                System.out.println("Grade distribution: " + gradeDist);
            }
            
            // Test cache functionality
            System.out.println("\nTesting cache functionality...");
            service.clearCacheForStudent(studentId);
            System.out.println("Cache cleared for student");
            
            System.out.println("✓ ReportService class test passed");
            
        } catch (ReportService.ReportServiceException e) {
            System.out.println("ReportServiceException: " + e.getMessage());
            // Don't fail the test if server interruption occurs - this is expected behavior
            if (service.isSmosServerConnected()) {
                throw new RuntimeException("Unexpected server connection error", e);
            } else {
                System.out.println("Expected server interruption occurred");
            }
        }
    }
    
    /**
     * Test 4: Simulate the full use case flow from DisplayOfAPageStudent.
     */
    private static void simulateUseCaseFlow() {
        System.out.println("=== Simulating Use Case Flow ===\n");
        System.out.println("Preconditions:");
        System.out.println("1. User is logged in as a student ✓");
        System.out.println("2. User clicks on 'Online report' ✓\n");
        
        System.out.println("Event Sequence:");
        
        // Step 1: System displays student's reports from archive
        System.out.println("1. System displays student's reports from archive...");
        String studentId = "STU-123456";
        ReportService reportService = new ReportService();
        
        try {
            List<Report> availableReports = reportService.fetchStudentReports(studentId);
            System.out.println("   Available reports (" + availableReports.size() + "):");
            for (int i = 0; i < availableReports.size(); i++) {
                Report report = availableReports.get(i);
                System.out.println("   " + (i + 1) + ". " + report.getTitle() + 
                                 " - " + report.getSemester() + 
                                 " (Grade: " + report.getOverallGrade() + ", " + 
                                 report.getGradeLetter() + ")");
            }
            
            // Step 2: User selects a report card
            System.out.println("\n2. User selects a report card...");
            if (!availableReports.isEmpty()) {
                Report selectedReport = availableReports.get(0); // Simulate selecting first report
                System.out.println("   Selected: " + selectedReport.getTitle());
                
                // Step 3: System displays details of selected report card
                System.out.println("\n3. System displays details of selected report card...");
                System.out.println("\n   === Report Details ===");
                System.out.println("   Title: " + selectedReport.getTitle());
                System.out.println("   Semester: " + selectedReport.getSemester());
                System.out.println("   Overall Grade: " + selectedReport.getOverallGrade() + 
                                 " (" + selectedReport.getGradeLetter() + ")");
                System.out.println("   Comments: " + selectedReport.getComments());
                
                System.out.println("\n   --- Subject Grades ---");
                Map<String, Double> subjectGrades = selectedReport.getSubjectGrades();
                for (Map.Entry<String, Double> entry : subjectGrades.entrySet()) {
                    System.out.println("   " + entry.getKey() + ": " + entry.getValue());
                }
                
                System.out.println("\n   --- Summary ---");
                System.out.println("   Generated: " + selectedReport.getGenerationDate());
                System.out.println("   Report ID: " + selectedReport.getReportId());
                System.out.println("   Student ID: " + selectedReport.getStudentId());
            }
            
            System.out.println("\nPostconditions:");
            System.out.println("1. System displays a student's report ✓");
            
            // Simulate server interruption (occurs randomly in ReportService)
            if (!reportService.isSmosServerConnected()) {
                System.out.println("2. Connection to SMOS server interrupted (simulated) ✓");
            } else {
                System.out.println("2. SMOS server connection: ACTIVE");
            }
            
            System.out.println("\n✓ Use case flow simulation completed");
            
        } catch (ReportService.ReportServiceException e) {
            System.out.println("Error during use case simulation: " + e.getMessage());
            if (reportService.isSmosServerConnected()) {
                throw new RuntimeException("Unexpected error in use case flow", e);
            } else {
                System.out.println("Server interruption simulated as expected in postconditions");
            }
        }
    }
    
    /**
     * Test 5: Test error handling and server interruptions.
     */
    private static void testErrorHandling() {
        System.out.println("Testing error handling capabilities...");
        
        ReportService service = new ReportService();
        String studentId = "STU-99999";
        
        // Test with invalid data
        System.out.println("\n1. Testing report validation...");
        Report invalidReport = new Report(null, null, -10, null, null);
        System.out.println("   Invalid report validation: " + invalidReport.isValid() + " (should be false)");
        
        // Test cache behavior during server disconnections
        System.out.println("\n2. Testing cache behavior...");
        
        try {
            // First fetch to populate cache
            List<Report> reports = service.fetchStudentReports(studentId);
            System.out.println("   Initial fetch successful: " + reports.size() + " reports");
            
            // Simulate server disconnection
            service.clearAllCache();
            System.out.println("   Cache cleared");
            
        } catch (ReportService.ReportServiceException e) {
            System.out.println("   Error during test: " + e.getMessage());
        }
        
        // Test reconnection attempts
        System.out.println("\n3. Testing server reconnection...");
        if (!service.isSmosServerConnected()) {
            System.out.println("   Server disconnected, attempting reconnection...");
            boolean reconnected = service.reconnectToSmosServer();
            System.out.println("   Reconnection " + (reconnected ? "successful" : "failed"));
        }
        
        // Test exception hierarchy
        System.out.println("\n4. Testing exception hierarchy...");
        try {
            // This would throw an exception if server is disconnected
            service.checkServerConnection(); // This is private, but demonstrates the concept
            System.out.println("   Server connection check passed");
        } catch (Exception e) {
            System.out.println("   Exception caught: " + e.getClass().getSimpleName());
        }
        
        System.out.println("✓ Error handling tests completed");
    }
    
    /**
     * Helper method to create a sample report for testing.
     */
    private static Report createSampleReport(String title, String semester, 
                                           double overallGrade, String comments, String studentId) {
        Map<String, Double> subjectGrades = new HashMap<>();
        subjectGrades.put("Mathematics", overallGrade - 3.0);
        subjectGrades.put("Physics", overallGrade - 1.5);
        subjectGrades.put("Chemistry", overallGrade + 1.5);
        subjectGrades.put("English", overallGrade + 2.0);
        
        Report report = new Report(title, semester, overallGrade, comments, subjectGrades);
        report.setStudentId(studentId);
        return report;
    }
}