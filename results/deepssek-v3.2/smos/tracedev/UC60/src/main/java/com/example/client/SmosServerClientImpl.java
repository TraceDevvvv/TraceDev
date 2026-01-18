package com.example.client;

import com.example.external.ExternalStudentData;
import java.util.ArrayList;
import java.util.Date;

/**
 * Implementation of SMOS server client.
 */
public class SmosServerClientImpl implements SmosServerClient {
    private Connection connection;
    
    public SmosServerClientImpl() {
        this.connection = new Connection();
    }
    
    @Override
    public ExternalStudentData fetchData(String studentId) {
        // Simulate connecting to SMOS server
        connection.connect();
        
        // Simulate fetching data (in real implementation, this would make an API call)
        ExternalStudentData data = new ExternalStudentData();
        
        // Create mock data as per sequence diagram requirements
        data.setAbsenceRecords(new ArrayList<>());
        data.setDisciplinaryNotes(new ArrayList<>());
        data.setDelayRecords(new ArrayList<>());
        data.setJustification("Medical certificate provided");
        
        return data;
    }
    
    @Override
    public void disconnect() {
        // Close connection to SMOS server
        connection.close();
        System.out.println("Disconnected from SMOS server");
    }
    
    /**
     * Helper class representing a connection.
     */
    private static class Connection {
        public void connect() {
            System.out.println("Connected to SMOS server");
        }
        
        public void close() {
            System.out.println("Closed connection to SMOS server");
        }
    }
}