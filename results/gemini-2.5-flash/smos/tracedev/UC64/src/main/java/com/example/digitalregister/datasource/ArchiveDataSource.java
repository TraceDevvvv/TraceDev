package com.example.digitalregister.datasource;

import com.example.digitalregister.exceptions.DataAccessError;
import com.example.digitalregister.model.DigitalRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Simulates an external archive data source for digital registers.
 * It can simulate data retrieval and connection errors.
 */
public class ArchiveDataSource {
    // Flag to simulate a connection interruption
    private boolean simulateConnectionError = false;

    // Dummy data for registers, keyed by academic year ID
    private final Map<String, List<DigitalRegister>> dummyRegisters;

    /**
     * Constructs an ArchiveDataSource and initializes with dummy data.
     */
    public ArchiveDataSource() {
        dummyRegisters = new HashMap<>();

        // Data for "2023-2024"
        List<DigitalRegister> registers2023_2024 = new ArrayList<>();
        registers2023_2024.add(new DigitalRegister("DR001", "AY2023-2024", "AC001", "Attendance Register 5A", "/archives/2023-2024/5A_attendance.pdf"));
        registers2023_2024.add(new DigitalRegister("DR002", "AY2023-2024", "AC002", "Grading Register 5B", "/archives/2023-2024/5B_grades.xlsx"));
        registers2023_2024.add(new DigitalRegister("DR003", "AY2023-2024", "AC001", "Homework Register 5A", "/archives/2023-2024/5A_homework.docx"));
        dummyRegisters.put("AY2023-2024", registers2023_2024);

        // Data for "2022-2023"
        List<DigitalRegister> registers2022_2023 = new ArrayList<>();
        registers2022_2023.add(new DigitalRegister("DR004", "AY2022-2023", "AC003", "Attendance Register 6C", "/archives/2022-2023/6C_attendance.pdf"));
        registers2022_2023.add(new DigitalRegister("DR005", "AY2022-2023", "AC004", "Grading Register 6D", "/archives/2022-2023/6D_grades.xlsx"));
        dummyRegisters.put("AY2022-2023", registers2022_2023);
    }

    /**
     * Simulates retrieving digital registers from the archive for a given academic year.
     * @param yearId The ID of the academic year.
     * @return A list of DigitalRegister objects.
     * @throws DataAccessError If a connection error is simulated.
     */
    public List<DigitalRegister> retrieveRegisters(String yearId) {
        // Simulate network delay
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Flow of Events 4: System searches in the archive.
        System.out.println("[ArchiveDataSource] Retrieving registers for academic year: " + yearId);

        if (simulateConnectionError) {
            // Exit Condition: Connection to SMOS server IS interrupted.
            throw new DataAccessError("Connection to archive data source interrupted.");
        }

        // Return dummy data or an empty list if yearId not found
        List<DigitalRegister> result = dummyRegisters.getOrDefault(yearId, new ArrayList<>());
        System.out.println("[ArchiveDataSource] Found " + result.size() + " registers for " + yearId);
        return result;
    }

    /**
     * Sets whether to simulate a connection error for subsequent retrieveRegisters calls.
     * @param simulateConnectionError True to simulate error, false otherwise.
     */
    public void setSimulateConnectionError(boolean simulateConnectionError) {
        this.simulateConnectionError = simulateConnectionError;
    }
}