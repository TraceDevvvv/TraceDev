package com.example.studentdelay.dataaccess;

import com.example.studentdelay.util.ConnectionException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * SMOSServerConnector simulates interaction with an external SMOS server.
 * It manages an in-memory list of data to mimic persistent storage.
 */
public class SMOSServerConnector {
    // In-memory simulation of SMOS server data
    private final List<Map<String, Object>> smosDatabase = new ArrayList<>();
    private final Random random = new Random();
    private int nextRecordId = 1;

    // Static data to pre-populate SMOS "database"
    public SMOSServerConnector() {
        // Sample data for testing
        Map<String, Object> record1 = new HashMap<>();
        record1.put("id", String.valueOf(nextRecordId++));
        record1.put("studentId", "S001");
        record1.put("delayDate", "2023-10-26");
        record1.put("reason", "Traffic jam");
        record1.put("entryTimestamp", "2023-10-26T08:35:00");
        smosDatabase.add(record1);

        Map<String, Object> record2 = new HashMap<>();
        record2.put("id", String.valueOf(nextRecordId++));
        record2.put("studentId", "S002");
        record2.put("delayDate", "2023-10-26");
        record2.put("reason", "Overslept");
        record2.put("entryTimestamp", "2023-10-26T08:40:00");
        smosDatabase.add(record2);

        Map<String, Object> record3 = new HashMap<>();
        record3.put("id", String.valueOf(nextRecordId++));
        record3.put("studentId", "S001");
        record3.put("delayDate", "2023-10-25");
        record3.put("reason", "Bus breakdown");
        record3.put("entryTimestamp", "2023-10-25T08:20:00");
        smosDatabase.add(record3);

        // Sample student data (separate for findStudentById)
        Map<String, Object> student1 = new HashMap<>();
        student1.put("studentId", "S001");
        student1.put("name", "Alice Wonderland");
        student1.put("parentContactInfo", "alice.parent@example.com");
        smosDatabase.add(student1);

        Map<String, Object> student2 = new HashMap<>();
        student2.put("studentId", "S002");
        student2.put("name", "Bob The Builder");
        student2.put("parentContactInfo", "bob.parent@example.com");
        smosDatabase.add(student2);
    }

    /**
     * Simulates sending data to the SMOS server.
     * Can randomly throw a ConnectionException to simulate network issues.
     *
     * @param data A map containing the data to be sent (e.g., DelayRecord or Student data).
     * @return A map confirming the data has been saved, typically including a generated ID.
     * @throws ConnectionException if the simulated connection fails.
     */
    public Map<String, Object> sendData(Map<String, Object> data) {
        // Simulate connection interruption (e.g., 10% chance of failure)
        if (random.nextInt(10) == 0) {
            throw new ConnectionException("Simulated SMOS server connection interrupted during send.");
        }

        System.out.println("SMOS Server Connector: Sending data to SMOS: " + data);
        // Simulate saving data. If it's a new delay record, assign an ID.
        if (data.containsKey("delayDate") && data.containsKey("studentId") && data.containsKey("reason")) {
            Map<String, Object> savedData = new HashMap<>(data);
            String newId = String.valueOf(nextRecordId++);
            savedData.put("id", newId); // SMOS generates ID
            smosDatabase.add(savedData);
            System.out.println("SMOS Server Connector: Data saved. Assigned ID: " + newId);
            return Collections.singletonMap("id", newId); // Return confirmation with new ID
        } else {
            // For other data types, just "save" and return success.
            smosDatabase.add(new HashMap<>(data)); // Add a copy to prevent external modification
            System.out.println("SMOS Server Connector: Data saved.");
            return Collections.singletonMap("status", "success");
        }
    }

    /**
     * Simulates fetching data from the SMOS server based on a query.
     * Can randomly throw a ConnectionException to simulate network issues.
     *
     * @param query A map containing query parameters (e.g., date, studentId).
     * @return A list of maps, where each map represents a record or entity.
     * @throws ConnectionException if the simulated connection fails.
     */
    public List<Map<String, Object>> fetchData(Map<String, Object> query) {
        // Simulate connection interruption (e.g., 10% chance of failure)
        if (random.nextInt(10) == 0) {
            throw new ConnectionException("Simulated SMOS server connection interrupted during fetch.");
        }

        System.out.println("SMOS Server Connector: Fetching data from SMOS with query: " + query);
        List<Map<String, Object>> results = new ArrayList<>();

        if (query.containsKey("delayDate")) {
            // Filter by delayDate
            String targetDate = (String) query.get("delayDate");
            for (Map<String, Object> record : smosDatabase) {
                if (record.containsKey("delayDate") && targetDate.equals(record.get("delayDate"))) {
                    results.add(record);
                }
            }
        } else if (query.containsKey("studentId")) {
            // Filter by studentId, assumes student data is also in smosDatabase
            String targetStudentId = (String) query.get("studentId");
            for (Map<String, Object> record : smosDatabase) {
                // Check if it's a student record AND matches the ID
                if (record.containsKey("studentId") && targetStudentId.equals(record.get("studentId"))
                        && record.containsKey("name") && record.containsKey("parentContactInfo")) {
                    results.add(record);
                    break; // Assuming only one student record per ID
                }
            }
        }
        System.out.println("SMOS Server Connector: Fetched " + results.size() + " records.");
        return results;
    }
}