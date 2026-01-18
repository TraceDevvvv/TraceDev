package com.example.smos.server;

import java.util.HashSet;
import java.util.Set;

/**
 * Simulates the SMOS (Student Management and Operations System) server.
 * This class handles server-side operations, particularly simulating the
 * interruption of a connection for a student whose registration request
 * has been rejected.
 */
public class SMOSServer {

    // In a real system, this would manage active connections or user sessions.
    // For simulation, we'll just track student IDs for whom connections were "interrupted".
    private final Set<String> interruptedStudentConnections;

    /**
     * Constructs a new SMOSServer instance.
     */
    public SMOSServer() {
        this.interruptedStudentConnections = new HashSet<>();
        System.out.println("[SMOSServer] SMOS Server initialized and running.");
    }

    /**
     * Simulates the interruption of a connection for a student whose registration
     * request has been rejected. This fulfills the postcondition:
     * "The user interrupts the connection operation to the interrupted SMOS server".
     *
     * @param studentId The unique identifier of the student whose connection needs to be interrupted.
     * @throws IllegalArgumentException if studentId is null or empty.
     */
    public void interruptConnectionForRejectedStudent(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty for connection interruption.");
        }

        // In a real scenario, this would involve closing network sockets,
        // invalidating sessions, or revoking access tokens.
        // Here, we simply record that the connection for this student was "interrupted".
        if (interruptedStudentConnections.add(studentId)) {
            System.out.println("[SMOSServer] Connection for student ID '" + studentId + "' has been interrupted due to registration rejection.");
        } else {
            System.out.println("[SMOSServer] Warning: Connection for student ID '" + studentId + "' was already marked as interrupted.");
        }
    }

    /**
     * Checks if a student's connection has been marked as interrupted.
     * This is a helper method for verification in the simulation.
     *
     * @param studentId The unique identifier of the student.
     * @return true if the student's connection was interrupted, false otherwise.
     */
    public boolean isConnectionInterrupted(String studentId) {
        return interruptedStudentConnections.contains(studentId);
    }

    /**
     * Simulates the server being shut down or becoming unavailable.
     */
    public void shutdown() {
        System.out.println("[SMOSServer] SMOS Server shutting down.");
        interruptedStudentConnections.clear(); // Clear any state on shutdown
    }
}