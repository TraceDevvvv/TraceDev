package com.example.schoolreports;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Simulates the external SMOS (School Management Operating System) API gateway.
 * This class is responsible for making requests to the "SMOS Server" and
 * returning raw data or throwing connection exceptions.
 */
public class SMOSGateway {

    // A flag to simulate connection failures for testing purposes.
    private boolean simulateConnectionFailure = false;
    private Random random = new Random();

    /**
     * Sets the flag to simulate connection failure.
     * @param simulateConnectionFailure true to simulate failure, false otherwise.
     */
    public void setSimulateConnectionFailure(boolean simulateConnectionFailure) {
        this.simulateConnectionFailure = simulateConnectionFailure;
        System.out.println("SMOSGateway: Connection failure simulation set to " + simulateConnectionFailure);
    }

    /**
     * Simulates requesting a list of student report summaries from SMOS.
     *
     * @param parentId The ID of the parent whose students' reports are requested.
     * @return A list of SMOSRawReportData containing summary information.
     * @throws SMOSConnectionException If a connection to SMOS server fails.
     */
    public List<SMOSRawReportData> requestReportsFromSMOS(String parentId) throws SMOSConnectionException {
        System.out.println("SMOSGateway: Requesting reports from SMOS for parentId: " + parentId);
        // Simulate network delay or processing time
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (simulateConnectionFailure) {
            // Simulate random connection failure on some requests even if flag is false.
            // For this diagram, we only trigger it when explicitly set to true.
            throw new SMOSConnectionException("Failed to connect to SMOS server (simulated error).");
        }

        // Simulate fetching raw data
        List<SMOSRawReportData> rawReports = new ArrayList<>();
        if ("parent123".equals(parentId)) {
            rawReports.add(new SMOSRawReportData("studentA456", "Alice Smith", "{ \"reportId\": \"RC001\", \"date\": \"2023-11-15\" }"));
            rawReports.add(new SMOSRawReportData("studentA456", "Alice Smith", "{ \"reportId\": \"RC002\", \"date\": \"2024-01-20\" }"));
            rawReports.add(new SMOSRawReportData("studentB789", "Bob Johnson", "{ \"reportId\": \"RC003\", \"date\": \"2023-12-01\" }"));
        } else {
            System.out.println("SMOSGateway: No reports found for parentId: " + parentId);
        }

        System.out.println("SMOSGateway: Successfully retrieved " + rawReports.size() + " raw reports from SMOS.");
        return rawReports;
    }

    /**
     * Simulates requesting detailed report card data from SMOS.
     *
     * @param reportCardId The ID of the specific report card to fetch details for.
     * @return SMOSRawReportCardData containing detailed information.
     * @throws SMOSConnectionException If a connection to SMOS server fails.
     */
    public SMOSRawReportCardData requestReportDetailsFromSMOS(String reportCardId) throws SMOSConnectionException {
        System.out.println("SMOSGateway: Requesting report details from SMOS for reportCardId: " + reportCardId);
        // Simulate network delay or processing time
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (simulateConnectionFailure) {
            // As per sequence diagram, this is where the connection interruption is simulated.
            System.err.println("SMOSGateway: Simulating connection interruption for reportCardId: " + reportCardId);
            throw new SMOSConnectionException("Connection to SMOS server interrupted while fetching details (simulated error).");
        }

        // Simulate fetching raw data
        String studentId = "";
        String studentName = "";
        String rawJson = "";

        switch (reportCardId) {
            case "RC001":
                studentId = "studentA456";
                studentName = "Alice Smith";
                rawJson = "{ \"term\": \"Fall\", \"year\": \"2023\", \"comments\": \"Alice is a diligent student.\", " +
                          "\"grades\": [{\"course\": \"Math\", \"grade\": \"A\", \"comment\": \"Excellent work.\"}, " +
                          "{\"course\": \"Science\", \"grade\": \"B+\", \"comment\": \"Good progress.\"}] }";
                break;
            case "RC002":
                studentId = "studentA456";
                studentName = "Alice Smith";
                rawJson = "{ \"term\": \"Winter\", \"year\": \"2024\", \"comments\": \"Alice continues to excel.\", " +
                          "\"grades\": [{\"course\": \"History\", \"grade\": \"A-\", \"comment\": \"Solid understanding.\"}, " +
                          "{\"course\": \"Art\", \"grade\": \"A\", \"comment\": \"Creative and expressive.\"}] }";
                break;
            case "RC003":
                studentId = "studentB789";
                studentName = "Bob Johnson";
                rawJson = "{ \"term\": \"Fall\", \"year\": \"2023\", \"comments\": \"Bob is making steady progress.\", " +
                          "\"grades\": [{\"course\": \"English\", \"grade\": \"C+\", \"comment\": \"Needs to read more.\"}, " +
                          "{\"course\": \"PE\", \"grade\": \"B\", \"comment\": \"Active participation.\"}] }";
                break;
            default:
                System.out.println("SMOSGateway: No report card details found for ID: " + reportCardId);
                // Return a dummy error or empty data if ID not found, but for this simulation,
                // we'll assume valid IDs are passed or an exception could be thrown for invalid IDs too.
                // For now, return a placeholder for unknown IDs.
                studentId = "unknown";
                studentName = "Unknown Student";
                rawJson = "{ \"error\": \"Report card not found\" }";
                break;
        }

        System.out.println("SMOSGateway: Successfully retrieved raw report card data for ID: " + reportCardId);
        return new SMOSRawReportCardData(reportCardId, studentId, rawJson);
    }
}