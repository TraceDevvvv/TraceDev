package com.example.attendancesystem;

import java.util.List;

/**
 * Gateway for interacting with an external server (e.g., SMOS).
 * Defined in the Infrastructure layer of the Class Diagram.
 * Can simulate network errors as per R16 and Sequence Diagram.
 */
public class ExternalServerGateway {
    private boolean simulateError = false; // Flag to simulate an ExternalServerError

    /**
     * Sets whether to simulate an error during the next call to sendData.
     * @param simulateError True to simulate an error, false otherwise.
     */
    public void setSimulateError(boolean simulateError) {
        this.simulateError = simulateError;
    }

    /**
     * Sends a list of absence records to the external SMOS server.
     * Sequence Diagram: Service -> ServerGateway : sendData(absenceData)
     * Sequence Diagram: ServerGateway -> SMOS : sendData(absenceData)
     * @param absenceData The list of AttendanceRecord objects representing absent students.
     * @throws ExternalServerError If there is a simulated connection issue or actual external server error (R16).
     */
    public void sendData(List<AttendanceRecord> absenceData) throws ExternalServerError {
        System.out.println("[ExternalServerGateway] Sending " + absenceData.size() + " absence records to SMOS server.");
        // Simulate network delay
        try { Thread.sleep(100); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        if (simulateError) {
            System.err.println("[ExternalServerGateway] Simulating ExternalServerError!");
            throw new ExternalServerError("Failed to connect to SMOS server.");
        }

        if (!absenceData.isEmpty()) {
            System.out.println("    Absence records sent to SMOS:");
            absenceData.forEach(r -> System.out.println("        " + r.getStudentId() + " - " + r.getStatus()));
        } else {
            System.out.println("    No absence data to send to SMOS.");
        }
    }
}