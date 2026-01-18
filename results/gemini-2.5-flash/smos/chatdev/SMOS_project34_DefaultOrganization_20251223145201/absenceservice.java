package com.chatdev.smos.service;
import com.chatdev.smos.model.Absence;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * Simulates a service layer responsible for retrieving student absence information.
 * This class provides mock data to demonstrate the application's functionality
 * without requiring a real backend connection.
 */
public class AbsenceService {
    // Simulates an active connection state (for the purpose of the postcondition)
    private static boolean serverConnected = false;
    /**
     * Simulates connecting to the SMOS server.
     * In a real application, this would establish a physical connection.
     */
    public static void connect() {
        System.out.println("Simulating connection to SMOS server...");
        serverConnected = true;
    }
    /**
     * Retrieves a list of absences for a specific student ID.
     * This method provides mock data for demonstration purposes, simulating
     * data fetched from a "School recorded" system as per the use case.
     *
     * @param studentId The ID of the student.
     * @return A list of Absence objects pertinent to the given student ID.
     * @throws IllegalStateException if the service is not 'connected'.
     */
    public static List<Absence> getStudentAbsences(String studentId) {
        if (!serverConnected) {
            throw new IllegalStateException("AbsenceService is not connected to the SMOS server.");
        }
        // Simulate a network delay or database query time to mimic real-world fetching.
        try {
            Thread.sleep(800); // Simulate a 0.8-second delay
        } catch (InterruptedException e) {
            // Restore the interrupted status
            Thread.currentThread().interrupt();
            System.err.println("AbsenceService interrupted during data fetching: " + e.getMessage());
        }
        List<Absence> absences = new ArrayList<>();
        // Hardcoded absences for a hypothetical student "S12345".
        // This data includes both justified (for green display) and
        // to-be-justified (for red display) absences.
        if ("S12345".equals(studentId)) {
            absences.add(new Absence(LocalDate.of(2023, 10, 5), "Fever and doctor's note", true)); // Justified
            absences.add(new Absence(LocalDate.of(2023, 10, 10), "Dental appointment", false)); // To Justify
            absences.add(new Absence(LocalDate.of(2023, 10, 15), "Family vacation", false)); // To Justify
            absences.add(new Absence(LocalDate.of(2023, 11, 2), "Sporting event participation", true)); // Justified
            absences.add(new Absence(LocalDate.of(2023, 11, 10), "Bereavement leave", true)); // Justified
            absences.add(new Absence(LocalDate.of(2023, 12, 1), "Feeling unwell, no doctor's note yet", false)); // To Justify
            absences.add(new Absence(LocalDate.of(2024, 1, 8), "Car trouble", false)); // To Justify
            absences.add(new Absence(LocalDate.of(2024, 1, 15), "Religious holiday", true)); // Justified
        } else {
            // Log if no data is found for a student, indicating an edge case or new student.
            System.out.println("No absences found for student ID: " + studentId + " in mock data.");
        }
        return absences;
    }
    /**
     * Simulates disconnecting from the SMOS server.
     * This explicitly addresses the postcondition "The administrator interrupts the connection to the SMOS server interrupted".
     * In a real system, this would release resources or close the network connection.
     */
    public static void disconnect() {
        System.out.println("SMOS server connection interrupted (simulated and explicitly disconnected).");
        serverConnected = false;
    }
}