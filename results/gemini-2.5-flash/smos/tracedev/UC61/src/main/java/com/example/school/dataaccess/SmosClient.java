package com.example.school.dataaccess;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Simulates an external SMOS (School Management Operating System) client.
 * This class handles low-level interactions like connecting, fetching raw data, and disconnecting.
 * It also includes dummy data for demonstration purposes.
 */
public class SmosClient {
    private String serverUrl;

    /**
     * Constructs a new SmosClient.
     * @param serverUrl The URL of the SMOS server.
     */
    public SmosClient(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    /**
     * Establishes a secure connection to the SMOS server.
     * Note: In a real system, this would involve network calls, authentication, and secure channel setup (e.g., TLS/SSL).
     *
     * @return A Connection object representing the established connection.
     */
    public Connection establishConnection() { // Renamed from connect
        System.out.println("SmosClient: Attempting to connect to " + serverUrl + "...");
        System.out.println("SmosClient: Establishing secure connection (e.g., via TLS/SSL).");
        // Simulate connection establishment
        Connection connection = new Connection(UUID.randomUUID().toString());
        System.out.println("SmosClient: Connection established: " + connection.getConnectionId());
        return connection;
    }

    /**
     * Disconnects from the SMOS server.
     * @param connection The connection to close.
     */
    public void closeConnection(Connection connection) { // Renamed from disconnect
        if (connection != null) {
            System.out.println("SmosClient: Closing connection " + connection.getConnectionId() + ".");
            // Simulate connection closing
            System.out.println("SmosClient: Connection closed.");
        } else {
            System.out.println("SmosClient: No active connection to close.");
        }
    }

    /**
     * Fetches raw data from the SMOS server based on a query.
     * This method simulates database interaction and returns raw data as a list of maps.
     *
     * @param query The query string (e.g., "SELECT * FROM Students WHERE id = '...'").
     * @param connection The active connection to use.
     * @return A list of maps, where each map represents a row of data with column names as keys.
     */
    public List<Map<String, Object>> fetchData(String query, Connection connection) {
        if (connection == null) {
            System.err.println("SmosClient: Cannot fetch data, connection is null.");
            return new ArrayList<>();
        }
        System.out.println("SmosClient: Fetching data with query: '" + query + "' using connection " + connection.getConnectionId());

        // Simulate fetching data based on query pattern
        List<Map<String, Object>> rawDataList = new ArrayList<>();

        if (query.contains("FROM Students")) {
            // Extract student ID from query (simple parsing for demonstration)
            String studentId = query.substring(query.indexOf("id = '") + 6, query.indexOf("'", query.indexOf("id = '") + 6));
            System.out.println("SmosClient: Querying for studentId: " + studentId);
            // Dummy student data
            if ("child123".equals(studentId)) {
                Map<String, Object> studentData = new HashMap<>();
                studentData.put("studentId", "child123");
                studentData.put("name", "Alice Wonderland");
                studentData.put("parentId", "parent456");
                rawDataList.add(studentData);
            }
        } else if (query.contains("FROM AcademicRecords")) {
            // Extract student ID from query
            String studentId = query.substring(query.indexOf("studentId = '") + 13, query.indexOf("'", query.indexOf("studentId = '") + 13));
            System.out.println("SmosClient: Querying for academic records for studentId: " + studentId);
            // Dummy academic record data
            if ("child123".equals(studentId)) {
                // Record 1
                Map<String, Object> record1 = new HashMap<>();
                record1.put("recordId", "rec001");
                record1.put("studentId", "child123");
                record1.put("recordDate", LocalDate.of(2023, 10, 26));
                record1.put("absences", 1);
                record1.put("disciplinaryNotes", "Late for class once.");
                record1.put("delayCount", 2);
                record1.put("justification", "Doctor appointment.");
                rawDataList.add(record1);

                // Record 2
                Map<String, Object> record2 = new HashMap<>();
                record2.put("recordId", "rec002");
                record2.put("studentId", "child123");
                record2.put("recordDate", LocalDate.of(2023, 11, 15));
                record2.put("absences", 0);
                record2.put("disciplinaryNotes", "");
                record2.put("delayCount", 0);
                record2.put("justification", "");
                rawDataList.add(record2);

                // Record 3
                Map<String, Object> record3 = new HashMap<>();
                record3.put("recordId", "rec003");
                record3.put("studentId", "child123");
                record3.put("recordDate", LocalDate.of(2023, 12, 1));
                record3.put("absences", 2);
                record3.put("disciplinaryNotes", "Missed a test.");
                record3.put("delayCount", 1);
                record3.put("justification", "Family emergency.");
                rawDataList.add(record3);
            }
        } else {
            System.out.println("SmosClient: Unknown query type. Returning empty list.");
        }

        System.out.println("SmosClient: Fetched " + rawDataList.size() + " records.");
        return rawDataList;
    }
}