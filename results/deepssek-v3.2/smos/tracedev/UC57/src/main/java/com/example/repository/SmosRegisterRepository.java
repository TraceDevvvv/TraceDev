package com.example.repository;

import com.example.model.SmosRegisterData;
import com.example.exception.ConnectionException;
import java.util.Map;
import java.util.HashMap;

/**
 * Repository implementation that fetches data from a SMOS server.
 */
public class SmosRegisterRepository implements RegisterRepository {
    private String serverEndpoint;

    public SmosRegisterRepository(String serverEndpoint) {
        this.serverEndpoint = serverEndpoint;
    }

    public String getServerEndpoint() {
        return serverEndpoint;
    }

    public void setServerEndpoint(String serverEndpoint) {
        this.serverEndpoint = serverEndpoint;
    }

    @Override
    public SmosRegisterData fetchRegisterData(String classId) throws ConnectionException {
        // Simulate connection to SMOS server
        // In a real scenario, this would make an HTTP request to serverEndpoint
        // For demonstration, we simulate both success and failure.

        // Simulate connection error (for sequence diagram alt path)
        // Uncomment to test connection exception:
        // throw new ConnectionException("Connection timeout", 500);

        // Normal flow: simulate fetching data
        Map<String, Object> rawData = new HashMap<>();
        rawData.put("className", "Mathematics 101");
        rawData.put("date", new java.util.Date());
        rawData.put("absences", new java.util.ArrayList<>());
        rawData.put("disciplinaryNotes", new java.util.ArrayList<>());
        rawData.put("delays", new java.util.ArrayList<>());
        rawData.put("justifications", new java.util.ArrayList<>());

        return new SmosRegisterData(rawData);
    }
}