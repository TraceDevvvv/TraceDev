import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * ReportService class providing business logic for managing student reports.
 * Handles fetching reports from the SMOS server, filtering, searching, and processing.
 * Includes error handling for server interruptions as specified in the use case.
 */
public class ReportService {
    
    // Connection status to SMOS server
    private boolean smosServerConnected;
    
    // In-memory cache for reports (in a real system, this would be a database)
    private Map<String, List<Report>> studentReportsCache;
    
    /**
     * Default constructor.
     * Initializes server connection and cache.
     */
    public ReportService() {
        this.smosServerConnected = true; // Assume connected initially
        this.studentReportsCache = new HashMap<>();
    }
    
    /**
     * Fetches all reports for a student from the SMOS server/archive.
     * This method simulates the "system displays the student's reports logged in in the archive"
     * from the use case events sequence.
     * 
     * @param studentId The ID of the student whose reports to fetch
     * @return List of reports for the student
     * @throws ReportServiceException if SMOS server connection is interrupted
     */
    public List<Report> fetchStudentReports(String studentId) throws ReportServiceException {
        System.out.println("Fetching reports for student: " + studentId);
        
        // Check SMOS server connection status
        try {
            checkServerConnection();
        } catch (ServerConnectionException e) {
            // Handle server interruption
            handleServerDisconnection();
            throw new ReportServiceException("SMOS server connection interrupted", e);
        }
        
        // Check cache first
        if (studentReportsCache.containsKey(studentId)) {
            System.out.println("Returning cached reports for student: " + studentId);
            return getCachedReports(studentId);
        }
        
        System.out.println("No cached reports found. Fetching from SMOS server...");
        
        try {
            // Simulate fetching from SMOS server/archive
            List<Report> reports = fetchReportsFromSmosServer(studentId);
            
            // Cache the results
            studentReportsCache.put(studentId, reports);
            
            // Postcondition: Connection might be interrupted after successful fetch
            simulatePossibleServerInterruption();
            
            return reports;
            
        } catch (ServerConnectionException e) {
            // Handle server interruption
            handleServerDisconnection();
            throw new ReportServiceException("Failed to fetch reports due to SMOS server interruption", e);
        }
    }
    
    /**
     * Fetches a specific report by student ID and report ID.
     * 
     * @param studentId The student's ID
     * @param reportId The report's ID
     * @return The requested report
     * @throws ReportServiceException if report not found or server error
     */
    public Report fetchReportById(String studentId, String reportId) throws ReportServiceException {
        System.out.println("Fetching report " + reportId + " for student " + studentId);
        
        // Check SMOS server connection status
        try {
            checkServerConnection();
        } catch (ServerConnectionException e) {
            // Handle server interruption
            handleServerDisconnection();
            throw new ReportServiceException("SMOS server connection interrupted", e);
        }
        
        try {
            List<Report> allReports = fetchStudentReports(studentId);
            
            // Find the specific report
            for (Report report : allReports) {
                if (report.getReportId().equals(reportId)) {
                    System.out.println("Found report: " + report.getTitle());
                    return report;
                }
            }
            
            throw new ReportServiceException("Report not found: " + reportId);
            
        } catch (ReportServiceException e) {
            // Check if it's a server connection issue
            if (!smosServerConnected) {
                System.out.println("Attempting to retrieve from cache due to server disconnection...");
                
                // Try to get from cache even though server is disconnected
                List<Report> cachedReports = getCachedReports(studentId);
                if (cachedReports != null) {
                    for (Report report : cachedReports) {
                        if (report.getReportId().equals(reportId)) {
                            System.out.println("Retrieved report from cache: " + report.getTitle());
                            return report;
                        }
                    }
                }
                throw new ReportServiceException("Report not available. SMOS server disconnected and not in cache.", e);
            }
            throw e;
        }
    }
    
    /**
     * Filters reports by semester for a specific student.
     * 
     * @param studentId The student's ID
     * @param semester Semester to filter by (e.g., "Fall 2023")
     * @return List of reports for the given semester
     * @throws ReportServiceException if server error occurs
     */
    public List<Report> getReportsBySemester(String studentId, String semester) throws ReportServiceException {
        try {
            List<Report> allReports = fetchStudentReports(studentId);
            
            return allReports.stream()
                .filter(report -> report.getSemester() != null && 
                                 report.getSemester().equalsIgnoreCase(semester))
                .collect(Collectors.toList());
            
        } catch (ReportServiceException e) {
            if (!smosServerConnected) {
                // Try to filter from cache
                System.out.println("Filtering from cache due to server disconnection...");
                List<Report> cachedReports = getCachedReports(studentId);
                if (cachedReports != null) {
                    return cachedReports.stream()
                        .filter(report -> report.getSemester() != null && 
                                         report.getSemester().equalsIgnoreCase(semester))
                        .collect(Collectors.toList());
                }
            }
            throw e;
        }
    }
    
    /**
     * Gets report statistics for a student.
     * 
     * @param studentId The student's ID
     * @return Map containing various statistics
     * @throws ReportServiceException if server error occurs
     */
    public Map<String, Object> getReportStatistics(String studentId) throws ReportServiceException {
        try {
            List<Report> reports = fetchStudentReports(studentId);
            
            Map<String, Object> stats = new HashMap<>();
            stats.put("totalReports", reports.size());
            
            if (!reports.isEmpty()) {
                double avgGrade = reports.stream()
                    .mapToDouble(Report::getOverallGrade)
                    .average()
                    .orElse(0.0);
                stats.put("averageGrade", avgGrade);
                
                // Count reports by grade letter
                Map<String, Long> gradeDistribution = reports.stream()
                    .collect(Collectors.groupingBy(
                        Report::getGradeLetter,
                        Collectors.counting()
                    ));
                stats.put("gradeDistribution", gradeDistribution);
                
                // Check for failing grades
                boolean hasFailing = reports.stream()
                    .anyMatch(Report::hasFailingGrades);
                stats.put("hasFailingGrades", hasFailing);
                
                // Get latest report date
                java.util.Optional<java.util.Date> latestDate = reports.stream()
                    .map(Report::getGenerationDate)
                    .max(java.util.Date::compareTo);
                stats.put("latestReportDate", latestDate.orElse(null));
            }
            
            return stats;
            
        } catch (ReportServiceException e) {
            if (!smosServerConnected) {
                System.out.println("Generating statistics from cache...");
                List<Report> cachedReports = getCachedReports(studentId);
                if (cachedReports != null && !cachedReports.isEmpty()) {
                    Map<String, Object> stats = new HashMap<>();
                    stats.put("totalReports", cachedReports.size());
                    stats.put("source", "cache (server disconnected)");
                    return stats;
                }
            }
            throw e;
        }
    }
    
    /**
     * Clears the cache for a specific student.
     * 
     * @param studentId The student's ID
     */
    public void clearCacheForStudent(String studentId) {
        studentReportsCache.remove(studentId);
        System.out.println("Cache cleared for student: " + studentId);
    }
    
    /**
     * Clears all cached reports.
     */
    public void clearAllCache() {
        studentReportsCache.clear();
        System.out.println("All cache cleared.");
    }
    
    /**
     * Checks the SMOS server connection status.
     * 
     * @return true if connected, false otherwise
     */
    public boolean isSmosServerConnected() {
        return smosServerConnected;
    }
    
    /**
     * Attempts to reconnect to the SMOS server.
     * 
     * @return true if reconnection successful, false otherwise
     */
    public boolean reconnectToSmosServer() {
        System.out.println("Attempting to reconnect to SMOS server...");
        
        // Simulate reconnection attempt
        try {
            Thread.sleep(1000); // Simulate network delay
            smosServerConnected = true;
            System.out.println("Successfully reconnected to SMOS server.");
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Reconnection interrupted.");
            return false;
        } catch (Exception e) {
            System.out.println("Failed to reconnect to SMOS server: " + e.getMessage());
            return false;
        }
    }
    
    // Private helper methods
    
    /**
     * Fetches reports from the SMOS server (simulated).
     * 
     * @param studentId The student's ID
     * @return List of reports for the student
     * @throws ServerConnectionException if server connection fails
     */
    private List<Report> fetchReportsFromSmosServer(String studentId) throws ServerConnectionException {
        // Simulate network delay
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ServerConnectionException("Fetch interrupted", e);
        }
        
        // Simulate random server failure for demonstration
        if (Math.random() < 0.05) { // 5% chance of server failure
            throw new ServerConnectionException("SMOS server connection failed unexpectedly");
        }
        
        // Generate sample reports (in a real system, this would be a database query)
        List<Report> reports = new ArrayList<>();
        
        // Sample report 1
        Map<String, Double> grades1 = new HashMap<>();
        grades1.put("Mathematics", 88.0);
        grades1.put("Physics", 82.0);
        grades1.put("Chemistry", 87.0);
        Report report1 = new Report("2023 Fall Semester", "Fall 2023", 85.5, 
                                   "Good performance overall", grades1);
        report1.setStudentId(studentId);
        reports.add(report1);
        
        // Sample report 2
        Map<String, Double> grades2 = new HashMap<>();
        grades2.put("Mathematics", 75.0);
        grades2.put("Physics", 80.0);
        grades2.put("English", 81.0);
        Report report2 = new Report("2023 Spring Semester", "Spring 2023", 78.5, 
                                   "Average performance", grades2);
        report2.setStudentId(studentId);
        reports.add(report2);
        
        // Sample report 3
        Map<String, Double> grades3 = new HashMap<>();
        grades3.put("Mathematics", 95.0);
        grades3.put("Physics", 91.0);
        grades3.put("Chemistry", 90.0);
        Report report3 = new Report("2022 Fall Semester", "Fall 2022", 92.0, 
                                   "Excellent performance", grades3);
        report3.setStudentId(studentId);
        reports.add(report3);
        
        System.out.println("Fetched " + reports.size() + " reports from SMOS server");
        return reports;
    }
    
    /**
     * Checks server connection and throws exception if disconnected.
     * 
     * @throws ServerConnectionException if server is disconnected
     */
    private void checkServerConnection() throws ServerConnectionException {
        if (!smosServerConnected) {
            throw new ServerConnectionException("SMOS server connection interrupted");
        }
    }
    
    /**
     * Handles server disconnection by updating status and logging.
     */
    private void handleServerDisconnection() {
        smosServerConnected = false;
        System.out.println("WARNING: Connection to SMOS server interrupted");
        System.out.println("Some features may be limited. Reports will be served from cache if available.");
    }
    
    /**
     * Simulates possible server interruption after operations.
     * This reflects the postcondition "Connection to the SMOS server interrupted".
     */
    private void simulatePossibleServerInterruption() {
        // 10% chance to simulate server interruption after an operation
        if (Math.random() < 0.1) {
            System.out.println("NOTICE: SMOS server connection interrupted after operation (simulated)");
            smosServerConnected = false;
        }
    }
    
    /**
     * Gets cached reports for a student.
     * 
     * @param studentId The student's ID
     * @return Cached reports or empty list if not in cache
     */
    private List<Report> getCachedReports(String studentId) {
        List<Report> cached = studentReportsCache.get(studentId);
        if (cached != null) {
            // Return a defensive copy
            return new ArrayList<>(cached);
        }
        return new ArrayList<>();
    }
    
    /**
     * Adds a report to the cache.
     * 
     * @param studentId The student's ID
     * @param report The report to cache
     */
    public void cacheReport(String studentId, Report report) {
        List<Report> reports = studentReportsCache.getOrDefault(studentId, new ArrayList<>());
        reports.add(report);
        studentReportsCache.put(studentId, reports);
        System.out.println("Report cached for student: " + studentId);
    }
    
    /**
     * Validates if a report exists for a student.
     * 
     * @param studentId The student's ID
     * @param reportId The report's ID
     * @return true if report exists and is accessible
     */
    public boolean validateReportAccess(String studentId, String reportId) {
        try {
            Report report = fetchReportById(studentId, reportId);
            return report != null && report.isValid();
        } catch (ReportServiceException e) {
            return false;
        }
    }
    
    /**
     * Exception class for report service errors.
     */
    public static class ReportServiceException extends Exception {
        public ReportServiceException(String message) {
            super(message);
        }
        
        public ReportServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    
    /**
     * Exception class for server connection errors.
     */
    public static class ServerConnectionException extends ReportServiceException {
        public ServerConnectionException(String message) {
            super(message);
        }
        
        public ServerConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}