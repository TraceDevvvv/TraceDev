package com.example.repositories;

import com.example.domain.Report;
import com.example.gateways.SmosServerGateway;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementation of ReportRepository that fetches data from SMOS server.
 */
public class SmosServerRepository implements ReportRepository {
    private String serverAddress;
    private SmosServerGateway gateway;
    
    public SmosServerRepository(String serverAddress) {
        this.serverAddress = serverAddress;
        this.gateway = new SmosServerGateway(serverAddress);
    }
    
    @Override
    public List<Report> getStudentReports(String studentId) {
        // delegate to fetchStudentReports for exact method name match
        return fetchStudentReports(studentId);
    }
    
    public List<Report> fetchStudentReports(String studentId) {
        try {
            // Connect to gateway
            if (!gateway.connect()) {
                throw new RuntimeException("Failed to connect to SMOS server");
            }
            
            // Fetch data
            String jsonData = gateway.fetchData("GET /reports/" + studentId);
            
            // Parse JSON to List<Report> (simplified parsing)
            return parseReportsFromJson(jsonData);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching student reports: " + e.getMessage(), e);
        }
    }
    
    @Override
    public Report getReportById(String reportId) {
        // delegate to fetchReportById for exact method name match
        return fetchReportById(reportId);
    }
    
    public Report fetchReportById(String reportId) {
        try {
            // Connect to gateway
            if (!gateway.connect()) {
                throw new RuntimeException("Failed to connect to SMOS server");
            }
            
            // Fetch data
            String jsonData = gateway.fetchData("GET /reports/" + reportId);
            
            // Parse JSON to Report object (simplified parsing)
            return parseReportFromJson(jsonData);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching report by ID: " + e.getMessage(), e);
        }
    }
    
    /**
     * Simplified JSON parsing for demonstration.
     */
    private List<Report> parseReportsFromJson(String jsonData) {
        List<Report> reports = new ArrayList<>();
        // In real implementation, use JSON parser like Jackson/Gson
        // For demo, create dummy reports
        reports.add(new Report("1", "Math Report", "Good progress in mathematics", new Date()));
        reports.add(new Report("2", "Science Report", "Excellent performance in science", new Date()));
        return reports;
    }
    
    /**
     * Simplified JSON parsing for demonstration.
     */
    private Report parseReportFromJson(String jsonData) {
        // In real implementation, parse JSON properly
        return new Report("1", "Math Report", "Detailed content: Student scored 95% in final exam.", new Date());
    }
}